package com.example.technicalservice.dto.user.requests;

import com.example.technicalservice.core.validations.ValidPassword;
import com.example.technicalservice.model.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserReq {
    @NotBlank
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @ValidPassword
    private String password;
    private List<Role> roles;
}
