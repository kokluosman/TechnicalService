package com.example.technicalservice.dto.sale.requests;

import com.example.technicalservice.model.Product;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleReq {
    @Size(min = 0,max = 1000000)
    private double price;
    @Length(min = 0,max = 300)
    private String note;
    private Product product;
    private boolean isSold;
}
