package com.example.technicalservice.dto.proposal.responses;

import com.example.technicalservice.dto.product.responses.ProductGetResponse;
import com.example.technicalservice.dto.user.responses.UserGetResponse;
import com.example.technicalservice.model.ProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProposalStatusGetResponse {

    private ProposalStatus proposalStatus;

    private ProductGetResponse product;

    private UserGetResponse user;

}
