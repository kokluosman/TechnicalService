package com.example.technicalservice.dto.proposal.requests;

import com.example.technicalservice.model.Product;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProposalReq {
    @Size(min = 50,max = 175000)
    private double price;
    @Length(max = 1500)
    private String note;

    private boolean proposalStatus;

    private Product product;

}
