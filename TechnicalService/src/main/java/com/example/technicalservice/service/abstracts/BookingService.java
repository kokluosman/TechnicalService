package com.example.technicalservice.service.abstracts;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.booking.requests.CreateBookingReq;
import com.example.technicalservice.dto.booking.requests.UpdateBookingReq;
import com.example.technicalservice.dto.booking.responses.BookingGetAllResponse;
import com.example.technicalservice.dto.booking.responses.BookingGetResponse;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    DataResult<List<BookingGetAllResponse>> getAllBookingAsc();
    DataResult<List<BookingGetAllResponse>> getAllBookingDesc();
    DataResult<List<BookingGetAllResponse>> getAllBookingDateAsc(LocalDate date);
    DataResult<List<BookingGetAllResponse>> getAllBookingDateDesc(LocalDate date);
    DataResult<List<BookingGetResponse>> getByBookingLikeName(String name);
    DataResult<BookingGetResponse> getById(long id);
    Result delete(long id);
    Result updateBooking(long id,UpdateBookingReq updateBookingReq);
    Result createBooking(CreateBookingReq createBookingReq);
    Result updateIsDone(long id);


}
