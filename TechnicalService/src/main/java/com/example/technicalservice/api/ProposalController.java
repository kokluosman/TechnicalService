package com.example.technicalservice.api;


import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.proposal.requests.CreateProposalReq;
import com.example.technicalservice.dto.proposal.requests.SetProposalStatusReq;
import com.example.technicalservice.dto.proposal.requests.UpdateProposalReq;
import com.example.technicalservice.dto.proposal.responses.ProposalGetAllResponse;
import com.example.technicalservice.dto.proposal.responses.ProposalGetResponse;
import com.example.technicalservice.dto.proposal.responses.ProposalStatusGetResponse;
import com.example.technicalservice.model.ProposalStatus;
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
    @GetMapping(path = "/getallproposal")
    DataResult<List<ProposalGetAllResponse>> getAllProposal(){
        return this.proposalService.getAllProposal();
    }
    @GetMapping(path = "/getproposal/{id}")
    DataResult<ProposalGetResponse> getProposal(@PathVariable(name = "id") long id){
        return this.proposalService.getProposal(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result deleteProposal(@PathVariable(name = "id") long id){
        return this.proposalService.deleteProposal(id);
    }
    @PutMapping(path = "setproposalstatus/{id}")
    Result setProposalStatus(@PathVariable(name = "id") long id,
                             @RequestHeader(name = "status") SetProposalStatusReq setProposalStatusReq){
        return this.proposalService.setProposalStatus(id, setProposalStatusReq);
    }
    @PostMapping(path = "/create")
    Result createProposal(@Valid @RequestBody CreateProposalReq createProposalReq){
        return this.proposalService.createProposal(createProposalReq);
    }
    @PutMapping(path = "/update")
    DataResult<UpdateProposalReq> updateProposal(@RequestParam(name = "id") long id,
                                                 @Valid @RequestBody UpdateProposalReq updateProposalReq){
        return this.proposalService.updateProposal(id, updateProposalReq);
    }
    @GetMapping(path = "/getByUser/{id}")
    DataResult<List<ProposalGetResponse>> getByUserId(@PathVariable(name = "id") long id){
        return this.proposalService.getByUserId(id);
    }
    @GetMapping(path = "/getallproposalstatus")
    DataResult<List<ProposalStatusGetResponse>> getAllProposalStatus(@RequestHeader(name = "status")
                                                             ProposalStatus status){
        return this.proposalService.getAllProposalStatus(status);
    }

}
