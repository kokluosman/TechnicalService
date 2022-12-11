package com.example.technicalservice.dto.sale.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetSoldReq {
    private boolean isSold;
}
