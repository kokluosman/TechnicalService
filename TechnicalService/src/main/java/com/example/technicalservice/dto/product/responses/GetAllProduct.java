package com.example.technicalservice.dto.product.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProduct {

    private long id;

    private String name;

}
