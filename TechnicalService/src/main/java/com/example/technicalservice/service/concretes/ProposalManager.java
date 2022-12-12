package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.ProposalRepository;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.GetAllProposal;
import com.example.technicalservice.dto.proposal.responses.GetProposal;
import com.example.technicalservice.model.Proposal;
import com.example.technicalservice.service.abstracts.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProposalManager implements ProposalService {

    private final ProposalRepository repository;

    @Override
    public DataResult<List<GetAllProposal>> getAllProposal() {
        List<Proposal> proposals = this.repository.findAll();
        List<GetAllProposal> allProposals = new ArrayList<>();
        for (Proposal proposal : proposals) {
            GetAllProposal getAllProposal = new GetAllProposal();
            getAllProposal.setId(proposal.getId());
            getAllProposal.setProposalStatus(proposal.isProposalStatus());
            getAllProposal.setNote(proposal.getNote());
            getAllProposal.setPrice(proposal.getPrice());
            getAllProposal.setProduct(proposal.getProduct());
            getAllProposal.setUser(proposal.getUser());
            allProposals.add(getAllProposal);
        }
        return new SuccessDataResult<>(allProposals,"Proposals is Successfully Listed!");
    }

    @Override
    public DataResult<GetProposal> getProposal(long id) {
        isNotExist(id);
        GetProposal getProposal = new GetProposal();
        Proposal proposal = this.repository.findById(id).get();
        getProposal.setNote(proposal.getNote());
        getProposal.setProposalStatus(proposal.isProposalStatus());
        getProposal.setProduct(proposal.getProduct());
        getProposal.setPrice(proposal.getPrice());
        getProposal.setUser(proposal.getUser());
        return new SuccessDataResult<>(getProposal,"Successfully get byProposal ID");
    }

    @Override
    public Result deleteProposal(long id) {
        isNotExist(id);
        this.repository.deleteById(id);
        return new SuccessResult("Proposal Successfully Deleted!");
    }

    @Override
    public Result setProposalStatus(long id,SetProposalStatusReq setProposalStatusReq) {
        isNotExist(id);
        Proposal proposal = this.repository.findById(id).get();
        proposal.setProposalStatus(setProposalStatusReq.isProposalStatus());
        this.repository.save(proposal);
        return new SuccessResult("Proposal Status Successfully set!");
    }

    @Override
    public Result createProposal(CreateProposalReq createProposalReq) {
        Proposal proposal = new Proposal();
        proposal.setProposalStatus(createProposalReq.isProposalStatus());
        proposal.setProduct(createProposalReq.getProduct());
        proposal.setNote(createProposalReq.getNote());
        proposal.setPrice(createProposalReq.getPrice());
        proposal.setUser(createProposalReq.getUser());
        this.repository.save(proposal);
        return new SuccessResult("Proposal Successfully Created!");
    }

    @Override
    public DataResult<UpdateProposalReq> updateProposal(long id, UpdateProposalReq updateProposalReq) {
        isNotExist(id);
        Proposal proposal = this.repository.findById(id).get();
        proposal.setPrice(updateProposalReq.getPrice());
        proposal.setProposalStatus(updateProposalReq.isProposalStatus());
        proposal.setNote(updateProposalReq.getNote());
        proposal.setProduct(updateProposalReq.getProduct());
        proposal.setUser(updateProposalReq.getUser());
        this.repository.save(proposal);
        return new SuccessDataResult<>(updateProposalReq,"Proposal Successfully updated!");
    }

    void isNotExist(long id){
        if (!this.repository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
