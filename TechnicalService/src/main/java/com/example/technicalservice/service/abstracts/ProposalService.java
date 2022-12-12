package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.GetAllProposal;
import com.example.technicalservice.dto.proposal.responses.GetProposal;

import java.util.List;

public interface ProposalService {

    DataResult<List<GetAllProposal>> getAllProposal();
    DataResult<GetProposal> getProposal(long id);
    Result deleteProposal(long id);

    Result setProposalStatus(long id,SetProposalStatusReq setProposalStatusReq);
    Result createProposal(CreateProposalReq createProposalReq);
    DataResult<UpdateProposalReq> updateProposal(long id,UpdateProposalReq updateProposalReq);



}
