package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.ProductRepository;
import com.example.technicalservice.dto.product.requests.CreateProductReq;
import com.example.technicalservice.dto.product.requests.UpdateProductReq;
import com.example.technicalservice.dto.product.responses.GetAllProduct;
import com.example.technicalservice.dto.product.responses.GetProduct;
import com.example.technicalservice.model.Product;
import com.example.technicalservice.service.abstracts.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductManager implements ProductService {

    private final ProductRepository repository;

    @Override
    public DataResult<List<GetAllProduct>> getAllProduct() {
        List<Product> products = this.repository.findAll();
        List<GetAllProduct> getAllProducts = new ArrayList<>();
        for (Product product : products) {
            GetAllProduct getAllProduct = new GetAllProduct();
            getAllProduct.setId(product.getId());
            getAllProduct.setName(product.getName());
            getAllProducts.add(getAllProduct);
        }
        return new SuccessDataResult<>(getAllProducts,"Products are Successfully Listed!");
    }

    @Override
    public DataResult<GetProduct> getProduct(long id) {
        isNotExist(id);
        Product product = this.repository.findById(id).get();
        GetProduct getProduct = new GetProduct();
        getProduct.setName(product.getName());
        return new SuccessDataResult<>(getProduct,"Successfully Fetched");
    }

    @Override
    public Result createProduct(CreateProductReq createProductReq) {
        Product product = new Product();
        product.setName(createProductReq.getName());
        this.repository.save(product);
        return new SuccessResult("Successfully Created!");
    }

    @Override
    public Result deleteProduct(long id) {
        isNotExist(id);
        this.repository.deleteById(id);
        return new SuccessResult("Successfully Deleted!!");
    }

    @Override
    public Result updateProduct(long id, UpdateProductReq updateProductReq) {
        Product product = this.repository.findById(id).get();
        product.setName(updateProductReq.getName());
        this.repository.save(product);
        return new SuccessResult("Successfully Updated!!");
    }

    void isNotExist(long id){
        if (!this.repository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
