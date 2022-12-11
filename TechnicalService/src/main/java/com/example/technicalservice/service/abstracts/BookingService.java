package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.booking.requests.CreateBookingReq;
import com.example.technicalservice.dto.booking.requests.UpdateBookingReq;
import com.example.technicalservice.dto.booking.responses.GetAllBooking;
import com.example.technicalservice.dto.booking.responses.GetBooking;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    DataResult<List<GetAllBooking>> getAllBookingAsc();
    DataResult<List<GetAllBooking>> getAllBookingDesc();
    DataResult<List<GetAllBooking>> getAllBookingDateAsc(LocalDate date);
    DataResult<List<GetAllBooking>> getAllBookingDateDesc(LocalDate date);
    DataResult<List<GetBooking>> getByBookingLikeName(String name);
    DataResult<GetBooking> getById(long id);
    Result delete(long id);
    Result updateBooking(long id,UpdateBookingReq updateBookingReq);
    Result createBooking(CreateBookingReq createBookingReq);
    Result updateIsDone(long id);


}
