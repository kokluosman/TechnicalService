package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.salelog.requests.CreateSaleLogReq;
import com.example.technicalservice.dto.salelog.responses.GetAllSaleLog;
import com.example.technicalservice.dto.salelog.responses.GetSaleLog;
import com.example.technicalservice.service.abstracts.SaleLogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/salelog")
public class SaleLogController {

    private final SaleLogService saleLogService;

    @GetMapping(path = "/getall")
    DataResult<List<GetAllSaleLog>> getAllSale(){
        return this.saleLogService.getAllSale();
    }
    @GetMapping(path = "/getSale/{id}")
    DataResult<GetSaleLog> getSaleLog(@PathVariable(name = "id") long id){
        return this.saleLogService.getSaleLog(id);
    }
    @PostMapping(path = "/createSaleLog")
    Result createSaleLog(@Valid @RequestBody CreateSaleLogReq createSaleLogReq){
        return this.saleLogService.createSaleLog(createSaleLogReq);
    }
}
