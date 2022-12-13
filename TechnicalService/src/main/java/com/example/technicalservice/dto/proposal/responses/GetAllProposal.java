package com.example.technicalservice.dto.proposal.responses;

import com.example.technicalservice.dto.product.responses.GetProduct;
import com.example.technicalservice.dto.user.responses.GetUser;
import com.example.technicalservice.model.ProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllProposal {

    private long id;

    private double price;

    private String note;

    private ProposalStatus proposalStatus;

    private LocalDate date;

    private GetProduct product;

    private GetUser user;
}
