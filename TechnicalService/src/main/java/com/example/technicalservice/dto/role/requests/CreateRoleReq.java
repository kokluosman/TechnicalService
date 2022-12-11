package com.example.technicalservice.dto.role.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleReq {

    @NotBlank
    @Length(min = 0,max = 50)
    private String name;

}
