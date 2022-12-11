package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.sale.requests.CreateSaleReq;
import com.example.technicalservice.dto.sale.requests.UpdateSaleReq;
import com.example.technicalservice.dto.sale.responses.GetAllSale;
import com.example.technicalservice.dto.sale.responses.GetSale;

import java.util.List;

public interface SaleService {

    Result updateSale(long id, UpdateSaleReq updateSaleReq);
    Result createSale(CreateSaleReq createSaleReq);
    Result deleteSale(long id);
    DataResult<List<GetAllSale>> getAllSale();

    DataResult<List<GetAllSale>> getAllSaleIsSold();
    DataResult<List<GetSale>> getByProduct(long id);



}
