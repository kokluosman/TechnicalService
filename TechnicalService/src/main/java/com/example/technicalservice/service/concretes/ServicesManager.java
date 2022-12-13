package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.ServiceRepository;
import com.example.technicalservice.dto.service.responses.ServicesGetAllResponse;
import com.example.technicalservice.dto.service.responses.ServicesGetResponse;
import com.example.technicalservice.service.abstracts.ServicesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ServicesManager implements ServicesService {

    private final ServiceRepository repository;

    @Override
    public DataResult<List<ServicesGetAllResponse>> getAll() {
        List<com.example.technicalservice.model.Service> all = this.repository.findAll();
        List<ServicesGetAllResponse> serviceGetAllResponses = new ArrayList<>();
        for (com.example.technicalservice.model.Service service : all) {
            ServicesGetAllResponse getServices = new ServicesGetAllResponse();
            getServices.setId(service.getId());
            getServices.setName(service.getName());
            serviceGetAllResponses.add(getServices);
        }
        return new SuccessDataResult<>(serviceGetAllResponses,"Data Successfully Listed!");
    }

    @Override
    public DataResult<ServicesGetResponse> getServices(long id) {
        com.example.technicalservice.model.Service service = this.repository.findById(id).get();
        ServicesGetResponse servicesGetResponse = new ServicesGetResponse();
        servicesGetResponse.setName(service.getName());
        return new SuccessDataResult<>(servicesGetResponse,"Services is fetch");
    }

    @Override
    public Result delete(long id) {
        isNotExist(id);
        this.repository.deleteById(id);
        return new SuccessResult("Successfully Deleted!!");
    }

    void isNotExist(long id){
        if (!this.repository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
