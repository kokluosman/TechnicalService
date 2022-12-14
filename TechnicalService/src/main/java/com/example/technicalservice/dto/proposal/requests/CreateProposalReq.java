package com.example.technicalservice.dto.proposal.requests;

import com.example.technicalservice.model.Product;
import com.example.technicalservice.model.ProposalStatus;
import com.example.technicalservice.model.User;
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

    private Product product;

    private User user;

}
