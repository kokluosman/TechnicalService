package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.exceptions.BusinessException;
import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.ProposalRepository;
import com.example.technicalservice.dto.product.responses.GetProduct;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.GetAllProposal;
import com.example.technicalservice.dto.proposal.responses.GetProposal;
import com.example.technicalservice.dto.proposal.responses.GetProposalStatus;
import com.example.technicalservice.dto.user.responses.GetUser;
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
    public DataResult<List<GetAllProposal>> getAllProposal() {
        List<Proposal> all = this.repository.findAll();
        List<GetAllProposal> allProposals = new ArrayList<>();
        GetUser getUser = new GetUser();
        GetProduct getProduct = new GetProduct();

        for (Proposal proposal : all) {
            GetAllProposal getAllProposal = new GetAllProposal();
            getAllProposal.setId(proposal.getId());
            getAllProposal.setNote(proposal.getNote());
            getAllProposal.setPrice(proposal.getPrice());
            getUser.setName(proposal.getUser().getName());
            getUser.setRoles(proposal.getUser().getRoles());
            getUser.setEmail(proposal.getUser().getEmail());
            getAllProposal.setUser(getUser);
            getProduct.setName(proposal.getProduct().getName());
            getAllProposal.setProduct(getProduct);
            getAllProposal.setProposalStatus(proposal.getProposalStatus());
            getAllProposal.setDate(proposal.getDate());

            allProposals.add(getAllProposal);
        }
        manager.flush();
        return new SuccessDataResult<>(allProposals,"Proposals Successfully Listed!");
    }

    @Override
    public DataResult<GetProposal> getProposal(long id) {
        isNotExist(id);
        Proposal proposal = this.repository.findById(id).get();
        GetProposal getProposal = new GetProposal();
        GetUser user = new GetUser();
        GetProduct getProduct = new GetProduct();
        getProposal.setPrice(proposal.getPrice());
        getProposal.setNote(proposal.getNote());
        getProposal.setProposalStatus(proposal.getProposalStatus());
        user.setEmail(proposal.getUser().getEmail());
        user.setName(proposal.getUser().getName());
        user.setRoles(proposal.getUser().getRoles());
        getProposal.setUser(user);
        getProduct.setName(proposal.getProduct().getName());
        getProposal.setProduct(getProduct);
        getProposal.setDate(proposal.getDate());
        manager.flush();
        return new SuccessDataResult<>(getProposal,"Successfully called byId");
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
    public DataResult<List<GetProposal>> getByUserId(long id) {
        isNotExist(id);
        List<Proposal> allByUserId = this.repository.findAllByUserId(id);
        List<GetProposal> getProposals = new ArrayList<>();
        GetUser getUser = new GetUser();
        GetProduct getProduct = new GetProduct();
        for (Proposal proposal : allByUserId) {
            GetProposal getProposal = new GetProposal();
            getProposal.setDate(proposal.getDate());
            getProposal.setPrice(proposal.getPrice());
            getProposal.setProposalStatus(proposal.getProposalStatus());
            getProposal.setNote(proposal.getNote());
            getUser.setRoles(proposal.getUser().getRoles());
            getUser.setName(proposal.getUser().getName());
            getUser.setEmail(proposal.getUser().getEmail());
            getProposal.setUser(getUser);
            getProduct.setName(proposal.getProduct().getName());
            getProposal.setProduct(getProduct);

            getProposals.add(getProposal);
        }
        return new SuccessDataResult<>(getProposals,"Proposals Successfully Listed byId!!");
    }

    @Override
    public DataResult<List<GetProposalStatus>> getAllProposalStatus(ProposalStatus status) {
        List<Proposal> allByProposalStatus = this.repository.findAllByProposalStatus(status);
        List<GetProposalStatus> getProposals = new ArrayList<>();
        GetUser getUser = new GetUser();
        GetProduct getProduct = new GetProduct();
        for (Proposal byProposalStatus : allByProposalStatus) {
            GetProposalStatus getProposalStatus = new GetProposalStatus();
            getProposalStatus.setProposalStatus(byProposalStatus.getProposalStatus());
            getUser.setEmail(byProposalStatus.getUser().getEmail());
            getUser.setName(byProposalStatus.getUser().getName());
            getUser.setRoles(byProposalStatus.getUser().getRoles());
            getProduct.setName(byProposalStatus.getProduct().getName());
            getProposalStatus.setProduct(getProduct);
            getProposalStatus.setUser(getUser);
            getProposals.add(getProposalStatus);
        }
        return new SuccessDataResult<>(getProposals,"Proposals status are Successfully Listed!");
    }

    void isNotExist(long id){
        if (!this.repository.existsById(id)){
            throw new BusinessException("This id is not exist");
        }
    }
}
