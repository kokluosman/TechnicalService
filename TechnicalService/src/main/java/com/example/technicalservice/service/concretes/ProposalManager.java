package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.ProposalRepository;
import com.example.technicalservice.dto.product.responses.ProductGetResponse;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.ProposalGetAllResponse;
import com.example.technicalservice.dto.proposal.responses.ProposalGetResponse;
import com.example.technicalservice.dto.proposal.responses.ProposalStatusGetResponse;
import com.example.technicalservice.dto.user.responses.UserGetResponse;
import com.example.technicalservice.model.Proposal;
import com.example.technicalservice.model.ProposalStatus;
import com.example.technicalservice.service.abstracts.ProposalService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ProposalManager implements ProposalService {

    private final ProposalRepository repository;
    private final EntityManager manager;

    @Override
    @Transactional
    public DataResult<List<ProposalGetAllResponse>> getAllProposal() {
        List<Proposal> all = this.repository.findAll();
        List<ProposalGetAllResponse> allProposals = new ArrayList<>();
        UserGetResponse userGetResponse = new UserGetResponse();
        ProductGetResponse productGetResponse = new ProductGetResponse();

        for (Proposal proposal : all) {
            ProposalGetAllResponse proposalGetAllResponse = new ProposalGetAllResponse();
            proposalGetAllResponse.setId(proposal.getId());
            proposalGetAllResponse.setNote(proposal.getNote());
            proposalGetAllResponse.setPrice(proposal.getPrice());
            userGetResponse.setName(proposal.getUser().getName());
            userGetResponse.setRoles(proposal.getUser().getRoles());
            userGetResponse.setEmail(proposal.getUser().getEmail());
            proposalGetAllResponse.setUser(userGetResponse);
            productGetResponse.setName(proposal.getProduct().getName());
            proposalGetAllResponse.setProduct(productGetResponse);
            proposalGetAllResponse.setProposalStatus(proposal.getProposalStatus());
            proposalGetAllResponse.setDate(proposal.getDate());

            allProposals.add(proposalGetAllResponse);
        }
        manager.flush();
        return new SuccessDataResult<>(allProposals,"Proposals Successfully Listed!");
    }

    @Override
    public DataResult<ProposalGetResponse> getProposal(long id) {
        isNotExist(id);
        Proposal proposal = this.repository.findById(id).get();
        ProposalGetResponse proposalGetResponse = new ProposalGetResponse();
        UserGetResponse user = new UserGetResponse();
        ProductGetResponse productGetResponse = new ProductGetResponse();
        proposalGetResponse.setPrice(proposal.getPrice());
        proposalGetResponse.setNote(proposal.getNote());
        proposalGetResponse.setProposalStatus(proposal.getProposalStatus());
        user.setEmail(proposal.getUser().getEmail());
        user.setName(proposal.getUser().getName());
        user.setRoles(proposal.getUser().getRoles());
        proposalGetResponse.setUser(user);
        productGetResponse.setName(proposal.getProduct().getName());
        proposalGetResponse.setProduct(productGetResponse);
        proposalGetResponse.setDate(proposal.getDate());
        manager.flush();
        return new SuccessDataResult<>(proposalGetResponse,"Successfully called byId");
    }

    @Override
    public Result deleteProposal(long id) {
        isNotExist(id);
        this.repository.deleteById(id);
        return new SuccessResult("Proposal Successfully Deleted!");
    }

    @Override
    public Result setProposalStatus(long id, SetProposalStatusReq setProposalStatusReq) {
        isNotExist(id);
        Proposal proposal = this.repository.findById(id).get();
        proposal.setProposalStatus(setProposalStatusReq.getProposalStatus());
        this.repository.save(proposal);
        return new SuccessResult("Proposal Status is Successfully updated!");
    }

    @Override
    public Result createProposal(CreateProposalReq createProposalReq) {
        Proposal proposal = new Proposal();
        proposal.setProposalStatus(ProposalStatus.PENDING);
        proposal.setDate(LocalDate.now());
        proposal.setPrice(createProposalReq.getPrice());
        proposal.setNote(createProposalReq.getNote());
        proposal.setUser(createProposalReq.getUser());
        proposal.setProduct(createProposalReq.getProduct());

        return new SuccessResult("Proposal is Successfully Created!");
    }

    @Override
    public DataResult<UpdateProposalReq> updateProposal(long id, UpdateProposalReq updateProposalReq) {
        isNotExist(id);
        Proposal proposal = this.repository.findById(id).get();
        proposal.setProduct(updateProposalReq.getProduct());
        proposal.setDate(updateProposalReq.getDate());
        proposal.setPrice(updateProposalReq.getPrice());
        proposal.setNote(updateProposalReq.getNote());
        proposal.setProposalStatus(updateProposalReq.getProposalStatus());
        proposal.setUser(updateProposalReq.getUser());
        return new SuccessDataResult<>(updateProposalReq,"Proposal Successfully Updated!");
    }

    @Override
    public DataResult<List<ProposalGetResponse>> getByUserId(long id) {
        isNotExist(id);
        List<Proposal> allByUserId = this.repository.findAllByUserId(id);
        List<ProposalGetResponse> proposalGetResponses = new ArrayList<>();
        UserGetResponse userGetResponse = new UserGetResponse();
        ProductGetResponse productGetResponse = new ProductGetResponse();
        for (Proposal proposal : allByUserId) {
            ProposalGetResponse proposalGetResponse = new ProposalGetResponse();
            proposalGetResponse.setDate(proposal.getDate());
            proposalGetResponse.setPrice(proposal.getPrice());
            proposalGetResponse.setProposalStatus(proposal.getProposalStatus());
            proposalGetResponse.setNote(proposal.getNote());
            userGetResponse.setRoles(proposal.getUser().getRoles());
            userGetResponse.setName(proposal.getUser().getName());
            userGetResponse.setEmail(proposal.getUser().getEmail());
            proposalGetResponse.setUser(userGetResponse);
            productGetResponse.setName(proposal.getProduct().getName());
            proposalGetResponse.setProduct(productGetResponse);

            proposalGetResponses.add(proposalGetResponse);
        }
        return new SuccessDataResult<>(proposalGetResponses,"Proposals Successfully Listed byId!!");
    }

    @Override
    public DataResult<List<ProposalStatusGetResponse>> getAllProposalStatus(ProposalStatus status) {
        List<Proposal> allByProposalStatus = this.repository.findAllByProposalStatus(status);
        List<ProposalStatusGetResponse> getProposals = new ArrayList<>();
        UserGetResponse userGetResponse = new UserGetResponse();
        ProductGetResponse productGetResponse = new ProductGetResponse();
        for (Proposal byProposalStatus : allByProposalStatus) {
            ProposalStatusGetResponse proposalStatusGetResponse = new ProposalStatusGetResponse();
            proposalStatusGetResponse.setProposalStatus(byProposalStatus.getProposalStatus());
            userGetResponse.setEmail(byProposalStatus.getUser().getEmail());
            userGetResponse.setName(byProposalStatus.getUser().getName());
            userGetResponse.setRoles(byProposalStatus.getUser().getRoles());
            productGetResponse.setName(byProposalStatus.getProduct().getName());
            proposalStatusGetResponse.setProduct(productGetResponse);
            proposalStatusGetResponse.setUser(userGetResponse);
            getProposals.add(proposalStatusGetResponse);
        }
        return new SuccessDataResult<>(getProposals,"Proposals status are Successfully Listed!");
    }

    void isNotExist(long id){
        if (!this.repository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
