package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.SaleRepository;
import com.example.technicalservice.dto.sale.requests.CreateSaleReq;
import com.example.technicalservice.dto.sale.requests.UpdateSaleReq;
import com.example.technicalservice.dto.sale.responses.SaleGetAllResponse;
import com.example.technicalservice.dto.sale.responses.SaleGetResponse;
import com.example.technicalservice.model.Sale;
import com.example.technicalservice.service.abstracts.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SaleManager implements SaleService {

    private final SaleRepository saleRepository;

    @Override
    public Result updateSale(long id, UpdateSaleReq updateSaleReq) {
        Sale sale = this.saleRepository.findById(id).get();
        sale.setSold(false);
        sale.setNote(updateSaleReq.getNote());
        sale.setProduct(updateSaleReq.getProduct());
        sale.setPrice(updateSaleReq.getPrice());
        this.saleRepository.save(sale);
        return new SuccessResult("Sale is Successfully Updated!!");
    }

    @Override
    public Result createSale(CreateSaleReq createSaleReq) {
        Sale sale = new Sale();
        sale.setSold(createSaleReq.isSold());
        sale.setNote(createSaleReq.getNote());
        sale.setProduct(createSaleReq.getProduct());
        sale.setPrice(createSaleReq.getPrice());
        this.saleRepository.save(sale);
        return new SuccessResult("Sale is Successfully Created!!");
    }

    @Override
    public Result deleteSale(long id) {
        isNotExist(id);
        this.saleRepository.deleteById(id);
        return new SuccessResult("Sale is Successfully Deleted!");
    }

    @Override
    public DataResult<List<SaleGetAllResponse>> getAllSale() {
        List<Sale> all = this.saleRepository.findAll();
        List<SaleGetAllResponse> allSales = new ArrayList<>();
        for (Sale sale : all) {
            SaleGetAllResponse saleGetAllResponse = new SaleGetAllResponse();
            saleGetAllResponse.setSold(sale.isSold());
            saleGetAllResponse.setProduct(sale.getProduct());
            saleGetAllResponse.setId(sale.getId());
            saleGetAllResponse.setNote(sale.getNote());
            saleGetAllResponse.setPrice(sale.getPrice());

            allSales.add(saleGetAllResponse);
        }
        return new SuccessDataResult<>(allSales,"Sales are Successfully Listed!!");
    }

    @Override
    public DataResult<List<SaleGetAllResponse>> getAllSaleIsSold() {
        List<Sale> sales = this.saleRepository.findAllByIsSold(false);
        List<SaleGetAllResponse> allSales = new ArrayList<>();
        sales.stream()
                .map(sale -> {
                    SaleGetAllResponse saleGetAllResponse = new SaleGetAllResponse();
                    saleGetAllResponse.setPrice(sale.getPrice());
                    saleGetAllResponse.setNote(sale.getNote());
                    saleGetAllResponse.setProduct(sale.getProduct());
                    saleGetAllResponse.setId(sale.getId());
                    saleGetAllResponse.setSold(sale.isSold());
                    allSales.add(saleGetAllResponse);
                    return saleGetAllResponse;
                });
        return new SuccessDataResult<>(allSales,"Data Successfully Listed!");
    }

    @Override
    public DataResult<List<SaleGetResponse>> getByProduct(long id) {
        List<Sale> sales = this.saleRepository.findAllByProductIdAndIsSold(id, false);
        List<SaleGetResponse> saleGetResponses = new ArrayList<>();
        sales.forEach(sale -> {
            SaleGetResponse saleGetResponse = new SaleGetResponse();
            saleGetResponse.setNote(sale.getNote());
            saleGetResponse.setSold(sale.isSold());
            saleGetResponse.setProduct(sale.getProduct());
            saleGetResponse.setPrice(sale.getPrice());
            saleGetResponse.setId(sale.getId());
            saleGetResponses.add(saleGetResponse);
        });
        return new SuccessDataResult<>(saleGetResponses,"Data Successfully Listed!!");
    }

    void isNotExist(long id){
        if (!this.saleRepository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
