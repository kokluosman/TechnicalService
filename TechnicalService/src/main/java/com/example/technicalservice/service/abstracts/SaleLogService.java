package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.salelog.requests.CreateSaleLogReq;
import com.example.technicalservice.dto.salelog.responses.GetAllSaleLog;
import com.example.technicalservice.dto.salelog.responses.GetSaleLog;

import java.util.List;

public interface SaleLogService {

    DataResult<List<GetAllSaleLog>> getAllSale();
    DataResult<GetSaleLog> getSaleLog(long id);
    Result createSaleLog(CreateSaleLogReq createSaleLogReq);

}
