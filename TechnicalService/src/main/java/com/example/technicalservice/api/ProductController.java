package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.product.requests.CreateProductReq;
import com.example.technicalservice.dto.product.requests.UpdateProductReq;
import com.example.technicalservice.dto.product.responses.ProductGetAllResponse;
import com.example.technicalservice.dto.product.responses.ProductGetResponse;
import com.example.technicalservice.service.abstracts.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService service;

    @GetMapping(path = "/getall")
    DataResult<List<ProductGetAllResponse>> getAllProduct(){
        return this.service.getAllProduct();
    }
    @GetMapping(path = "/getById/{id}")
    DataResult<ProductGetResponse> getProduct(@PathVariable(name = "id") long id){
        return this.service.getProduct(id);
    }
    @PostMapping(path = "/create")
    Result createProduct(@Valid @RequestBody CreateProductReq createProductReq){
        return this.service.createProduct(createProductReq);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result deleteProduct(@PathVariable(name = "id") long id){
        return this.service.deleteProduct(id);
    }
    @PutMapping(path = "/update/{id}")
    Result updateProduct(@PathVariable(name = "id") long id,
                         @Valid @RequestBody UpdateProductReq updateProductReq){
        return this.service.updateProduct(id, updateProductReq);
    }
}
