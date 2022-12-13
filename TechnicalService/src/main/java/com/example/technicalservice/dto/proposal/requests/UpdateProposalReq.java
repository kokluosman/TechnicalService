package com.example.technicalservice.dto.proposal.requests;

import com.example.technicalservice.model.Product;
import com.example.technicalservice.model.ProposalStatus;
import com.example.technicalservice.model.User;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProposalReq {
    @Size(min = 50,max = 175000)
    private double price;
    @Length(max = 1500)
    private String note;

    private ProposalStatus proposalStatus;

    private LocalDate date;

    private Product product;

    private User user;
}
