package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.SaleRepository;
import com.example.technicalservice.dto.sale.requests.CreateSaleReq;
import com.example.technicalservice.dto.sale.requests.UpdateSaleReq;
import com.example.technicalservice.dto.sale.responses.GetAllSale;
import com.example.technicalservice.dto.sale.responses.GetSale;
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
    public DataResult<List<GetAllSale>> getAllSale() {
        List<Sale> all = this.saleRepository.findAll();
        List<GetAllSale> allSales = new ArrayList<>();
        for (Sale sale : all) {
            GetAllSale getAllSale = new GetAllSale();
            getAllSale.setSold(sale.isSold());
            getAllSale.setProduct(sale.getProduct());
            getAllSale.setId(sale.getId());
            getAllSale.setNote(sale.getNote());
            getAllSale.setPrice(sale.getPrice());

            allSales.add(getAllSale);
        }
        return new SuccessDataResult<>(allSales,"Sales are Successfully Listed!!");
    }

    @Override
    public DataResult<List<GetAllSale>> getAllSaleIsSold() {
        List<Sale> sales = this.saleRepository.findAllByIsSold(false);
        List<GetAllSale> allSales = new ArrayList<>();
        sales.stream()
                .map(sale -> {
                    GetAllSale getAllSale = new GetAllSale();
                    getAllSale.setPrice(sale.getPrice());
                    getAllSale.setNote(sale.getNote());
                    getAllSale.setProduct(sale.getProduct());
                    getAllSale.setId(sale.getId());
                    getAllSale.setSold(sale.isSold());
                    allSales.add(getAllSale);
                    return getAllSale;
                });
        return new SuccessDataResult<>(allSales,"Data Successfully Listed!");
    }

    @Override
    public DataResult<List<GetSale>> getByProduct(long id) {
        List<Sale> sales = this.saleRepository.findAllByProductIdAndIsSold(id, false);
        List<GetSale> getSales = new ArrayList<>();
        sales.forEach(sale -> {
            GetSale getSale = new GetSale();
            getSale.setNote(sale.getNote());
            getSale.setSold(sale.isSold());
            getSale.setProduct(sale.getProduct());
            getSale.setPrice(sale.getPrice());
            getSale.setId(sale.getId());
            getSales.add(getSale);
        });
        return new SuccessDataResult<>(getSales,"Data Successfully Listed!!");
    }

    void isNotExist(long id){
        if (!this.saleRepository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
