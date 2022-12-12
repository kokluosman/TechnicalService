package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.GetAllProposal;
import com.example.technicalservice.dto.proposal.responses.GetProposal;
import com.example.technicalservice.service.abstracts.ProposalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/proposal")
public class ProposalController {

    private final ProposalService proposalService;

    @GetMapping(path = "/getall")
    DataResult<List<GetAllProposal>> getAllProposal(){
        return this.proposalService.getAllProposal();
    }
    @GetMapping(path = "/getById/{id}")
    DataResult<GetProposal> getProposal(@PathVariable(name = "id") long id){
        return this.proposalService.getProposal(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result deleteProposal(@PathVariable(name = "id") long id){
        return this.proposalService.deleteProposal(id);
    }
    @PutMapping(path = "/setProposalStatus/{id}")
    Result setProposalStatus(@PathVariable(name = "id") long id,
                             @RequestBody SetProposalStatusReq setProposalStatusReq){
        return this.proposalService.setProposalStatus(id, setProposalStatusReq);
    }
    @PostMapping(path = "/create")
    Result createProposal(@Valid @RequestBody CreateProposalReq createProposalReq){
        return this.proposalService.createProposal(createProposalReq);
    }
    @PutMapping(path = "/update/{id}")
    DataResult<UpdateProposalReq> updateProposal(@PathVariable(name = "id") long id,
                                                 @Valid @RequestBody UpdateProposalReq updateProposalReq){
        return this.proposalService.updateProposal(id, updateProposalReq);
    }
}
