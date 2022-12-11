package com.example.technicalservice.dto.user.requests;

import com.example.technicalservice.core.validations.ValidPassword;
import com.example.technicalservice.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserReq {

    @NotBlank
    @Length(min = 2,max = 100)
    private String name;
    @Email
    @NotBlank
    private String email;
    @ValidPassword
    private String password;
    private List<Role> roles;
}
