package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.ServiceRepository;
import com.example.technicalservice.dto.service.responses.GetAllServices;
import com.example.technicalservice.dto.service.responses.GetServices;
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
    public DataResult<List<GetAllServices>> getAll() {
        List<com.example.technicalservice.model.Service> all = this.repository.findAll();
        List<GetAllServices> getAllServices = new ArrayList<>();
        for (com.example.technicalservice.model.Service service : all) {
            GetAllServices getServices = new GetAllServices();
            getServices.setId(service.getId());
            getServices.setName(service.getName());
            getAllServices.add(getServices);
        }
        return new SuccessDataResult<>(getAllServices,"Data Successfully Listed!");
    }

    @Override
    public DataResult<GetServices> getServices(long id) {
        com.example.technicalservice.model.Service service = this.repository.findById(id).get();
        GetServices getServices = new GetServices();
        getServices.setName(service.getName());
        return new SuccessDataResult<>(getServices,"Services is fetch");
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
