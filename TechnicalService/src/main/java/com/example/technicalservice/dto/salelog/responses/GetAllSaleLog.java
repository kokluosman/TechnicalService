package com.example.technicalservice.dto.salelog.responses;

import com.example.technicalservice.dto.sale.responses.GetSale;
import com.example.technicalservice.model.Sale;
import com.example.technicalservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllSaleLog {
    private long id;
    private Timestamp saleDate;
    private String creditCart;

    private User user;

    private Sale sale;
}
