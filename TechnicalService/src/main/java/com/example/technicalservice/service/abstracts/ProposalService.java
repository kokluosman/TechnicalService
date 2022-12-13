package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.ProposalGetAllResponse;
import com.example.technicalservice.dto.proposal.responses.ProposalGetResponse;
import com.example.technicalservice.dto.proposal.responses.ProposalStatusGetResponse;
import com.example.technicalservice.model.ProposalStatus;

import java.util.List;

public interface ProposalService {

    DataResult<List<ProposalGetAllResponse>> getAllProposal();
    DataResult<ProposalGetResponse> getProposal(long id);
    Result deleteProposal(long id);

    Result setProposalStatus(long id,SetProposalStatusReq setProposalStatusReq);
    Result createProposal(CreateProposalReq createProposalReq);
    DataResult<UpdateProposalReq> updateProposal(long id,UpdateProposalReq updateProposalReq);

    DataResult<List<ProposalGetResponse>> getByUserId(long id);
    DataResult<List<ProposalStatusGetResponse>> getAllProposalStatus(ProposalStatus status);

}
