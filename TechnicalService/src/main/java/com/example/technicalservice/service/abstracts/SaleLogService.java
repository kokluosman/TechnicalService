package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.salelog.requests.CreateSaleLogReq;
import com.example.technicalservice.dto.salelog.responses.SaleLogGetAllResponse;
import com.example.technicalservice.dto.salelog.responses.SaleLogGetResponse;

import java.util.List;

public interface SaleLogService {

    DataResult<List<SaleLogGetAllResponse>> getAllSale();
    DataResult<SaleLogGetResponse> getSaleLog(long id);
    Result createSaleLog(CreateSaleLogReq createSaleLogReq);

}
