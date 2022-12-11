package com.example.technicalservice.dto.sale.responses;

import com.example.technicalservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSale {

    private Long id;
    private double price;
    private String note;
    private Product product;
    private boolean isSold;
}
