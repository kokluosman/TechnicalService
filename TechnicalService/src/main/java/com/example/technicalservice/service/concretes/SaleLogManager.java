package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.SaleLogRepository;
import com.example.technicalservice.dataAccess.SaleRepository;
import com.example.technicalservice.dto.salelog.requests.CreateSaleLogReq;
import com.example.technicalservice.dto.salelog.responses.SaleLogGetAllResponse;
import com.example.technicalservice.dto.salelog.responses.SaleLogGetResponse;
import com.example.technicalservice.model.SaleLog;
import com.example.technicalservice.service.abstracts.SaleLogService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SaleLogManager implements SaleLogService {

    private final SaleLogRepository logRepository;
    private final SaleRepository saleRepository;
    private final EntityManager manager;

    @Override
    public DataResult<List<SaleLogGetAllResponse>> getAllSale() {
        List<SaleLog> logs = this.logRepository.findAll();
        List<SaleLogGetAllResponse> allSales = new ArrayList<>();
        for (SaleLog log : logs) {
            SaleLogGetAllResponse getAllSale = new SaleLogGetAllResponse();
            getAllSale.setSaleDate(log.getSaleDate());
            getAllSale.setId(log.getId());
            getAllSale.setUser(log.getUser());
            getAllSale.setCreditCart(log.getCreditCart());
            getAllSale.setSale(log.getSale());
            allSales.add(getAllSale);
        }
        return new SuccessDataResult<>(allSales,"SaleLogs are Successfully Listed!");
    }

    @Override
    public DataResult<SaleLogGetResponse> getSaleLog(long id) {
        SaleLog saleLog = this.logRepository.findById(id).get();
        SaleLogGetResponse saleLogGetResponse = new SaleLogGetResponse();
        saleLogGetResponse.setSaleDate(saleLog.getSaleDate());
        saleLogGetResponse.setUser(saleLog.getUser());
        saleLogGetResponse.setCreditCart(saleLog.getCreditCart());
        saleLogGetResponse.setSale(saleLog.getSale());
        return new SuccessDataResult<>(saleLogGetResponse,"SaleLog is Succesfully Listed!");
    }

    @Override
    public Result createSaleLog(CreateSaleLogReq createSaleLogReq) {
        SaleLog saleLog = new SaleLog();
        saleLog.setCreditCart(createSaleLogReq.getCreditCart());
        saleLog.setUser(createSaleLogReq.getUser());
        saleLog.setSaleDate(createSaleLogReq.getSaleDate());
        saleLog.setUser(createSaleLogReq.getUser());
        saleLog.setSale(createSaleLogReq.getSale());
        saleLog.setId(createSaleLogReq.getId());
        saleLog=logRepository.save(saleLog);
        logRepository.flush();
        manager.clear();
        saleLog=logRepository.findById(saleLog.getId()).get();
        saleLog.getSale().setSold(true);
        saleRepository.save(saleLog.getSale());
        return new SuccessResult("Log Successfully Created!");
    }
}
