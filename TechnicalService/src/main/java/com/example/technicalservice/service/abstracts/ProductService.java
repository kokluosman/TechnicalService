package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.product.requests.CreateProductReq;
import com.example.technicalservice.dto.product.requests.UpdateProductReq;
import com.example.technicalservice.dto.product.responses.GetAllProduct;
import com.example.technicalservice.dto.product.responses.GetProduct;

import java.util.List;

public interface ProductService {

    DataResult<List<GetAllProduct>> getAllProduct();
    DataResult<GetProduct> getProduct(long id);
    Result createProduct(CreateProductReq createProductReq);
    Result deleteProduct(long id);
    Result updateProduct(long id, UpdateProductReq updateProductReq);

}
