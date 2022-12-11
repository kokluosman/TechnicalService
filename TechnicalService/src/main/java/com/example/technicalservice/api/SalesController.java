package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.sale.requests.CreateSaleReq;
import com.example.technicalservice.dto.sale.requests.UpdateSaleReq;
import com.example.technicalservice.dto.sale.responses.GetAllSale;
import com.example.technicalservice.dto.sale.responses.GetSale;
import com.example.technicalservice.service.abstracts.SaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sale")
public class SalesController {

    private final SaleService saleService;

    @GetMapping(path = "/createSale")
    Result createSale(@Valid @RequestBody CreateSaleReq createSaleReq){
        return this.saleService.createSale(createSaleReq);
    }
    @DeleteMapping(path = "/delete/{}")
    Result deleteSale(@PathVariable(name = "id") long id){
        return this.saleService.deleteSale(id);
    }
    @GetMapping(path = "getall")
    DataResult<List<GetAllSale>> getAllSale(){
        return this.saleService.getAllSale();
    }
    @GetMapping(path = "/getallsaleIsSold")
    DataResult<List<GetAllSale>> getAllSaleIsSold(){
        return this.saleService.getAllSaleIsSold();
    }
    @GetMapping(path = "/getByProduct")
    DataResult<List<GetSale>> getByProduct(long id){
        return this.saleService.getByProduct(id);
    }
    @PutMapping(path = "/update/{id}")
    Result updateSale(@PathVariable(name = "id") long id,
                      @Valid @RequestBody UpdateSaleReq updateSaleReq){
        return this.saleService.updateSale(id, updateSaleReq);
    }

}
