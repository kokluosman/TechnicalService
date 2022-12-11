package com.example.technicalservice.dto.service.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllServices {

    private Long id;
    @NotBlank
    private String name;
}
