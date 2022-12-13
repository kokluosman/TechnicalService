package com.example.technicalservice.dto.proposal.responses;

import com.example.technicalservice.dto.product.responses.ProductGetResponse;
import com.example.technicalservice.dto.user.responses.UserGetResponse;
import com.example.technicalservice.model.ProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProposalGetResponse {

    private double price;

    private String note;

    private ProposalStatus proposalStatus;

    private ProductGetResponse product;

    private LocalDate date;

    private UserGetResponse user;
}
