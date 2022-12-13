package com.example.technicalservice.dto.proposal.requests;

import com.example.technicalservice.model.ProposalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetProposalStatusReq {

    private ProposalStatus proposalStatus;

}
