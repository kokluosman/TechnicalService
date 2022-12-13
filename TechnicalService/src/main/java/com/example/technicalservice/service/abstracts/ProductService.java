package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.product.requests.CreateProductReq;
import com.example.technicalservice.dto.product.requests.UpdateProductReq;
import com.example.technicalservice.dto.product.responses.ProductGetAllResponse;
import com.example.technicalservice.dto.product.responses.ProductGetResponse;

import java.util.List;

public interface ProductService {

    DataResult<List<ProductGetAllResponse>> getAllProduct();
    DataResult<ProductGetResponse> getProduct(long id);
    Result createProduct(CreateProductReq createProductReq);
    Result deleteProduct(long id);
    Result updateProduct(long id, UpdateProductReq updateProductReq);

}
