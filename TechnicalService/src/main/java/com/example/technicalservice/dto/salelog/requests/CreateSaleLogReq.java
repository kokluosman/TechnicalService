package com.example.technicalservice.dto.salelog.requests;

import com.example.technicalservice.model.Sale;
import com.example.technicalservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleLogReq {

    private Long id;

    private Timestamp saleDate;
    @Length(min = 16,max = 16)
    private String creditCart;

    private User user;

    private Sale sale;

}
