package com.example.technicalservice.dto.role.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllRoles {

    private Long id;

    private String name;
}
