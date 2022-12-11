package com.example.technicalservice.dto.booking.responses;

import com.example.technicalservice.model.Service;
import com.example.technicalservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBooking {
    private LocalDate bookingDate;

    private String note;

    private boolean isDone;

    private User user;
    private Service service;
}
