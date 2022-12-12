package com.example.technicalservice.dto.proposal.responses;

import com.example.technicalservice.model.Product;
import com.example.technicalservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProposal {

    private double price;

    private String note;

    private boolean proposalStatus;

    private Product product;

    private User user;
}
