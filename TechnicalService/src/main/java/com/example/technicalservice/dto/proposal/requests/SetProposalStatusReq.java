package com.example.technicalservice.dto.proposal.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetProposalStatusReq {

    private boolean proposalStatus;

}
