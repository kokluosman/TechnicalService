package com.example.technicalservice.dto.user.responses;

import com.example.technicalservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllUser {
    private long id;
    private String name;
    private String email;
    private String password;
    private List<Role> roles;
}
