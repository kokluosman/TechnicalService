package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.sale.requests.CreateSaleReq;
import com.example.technicalservice.dto.sale.requests.UpdateSaleReq;
import com.example.technicalservice.dto.sale.responses.SaleGetAllResponse;
import com.example.technicalservice.dto.sale.responses.SaleGetResponse;

import java.util.List;

public interface SaleService {

    Result updateSale(long id, UpdateSaleReq updateSaleReq);
    Result createSale(CreateSaleReq createSaleReq);
    Result deleteSale(long id);
    DataResult<List<SaleGetAllResponse>> getAllSale();

    DataResult<List<SaleGetAllResponse>> getAllSaleIsSold();
    DataResult<List<SaleGetResponse>> getByProduct(long id);



}
