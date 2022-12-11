package com.example.technicalservice.api;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.dto.booking.requests.CreateBookingReq;
import com.example.technicalservice.dto.booking.requests.UpdateBookingReq;
import com.example.technicalservice.dto.booking.responses.GetAllBooking;
import com.example.technicalservice.dto.booking.responses.GetBooking;
import com.example.technicalservice.service.abstracts.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping(path = "/getallAsc")
    DataResult<List<GetAllBooking>> getAllBookingAsc(){
        return this.bookingService.getAllBookingAsc();
    }
    @GetMapping(path = "/getallDesc")
    DataResult<List<GetAllBooking>> getAllBookingDesc(){
        return this.bookingService.getAllBookingDesc();
    }
    @GetMapping(path = "/getallBookingAsc/{dateTo}")
    DataResult<List<GetAllBooking>> getAllBookingDateAsc(@PathVariable(name = "dateTo")
                                                         @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                         LocalDate date){
        return this.bookingService.getAllBookingDateAsc(date);
    }
    @GetMapping(path = "/getallBookingDesc/{dateTo}")
    DataResult<List<GetAllBooking>> getAllBookingDateDesc(@PathVariable(name = "dateTo")
                                                          @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                          LocalDate date){
        return this.bookingService.getAllBookingDateDesc(date);
    }
    @GetMapping(path = "/getByUserName/{name}")
    DataResult<List<GetBooking>> getByBookingLikeName(@PathVariable(name = "name") String name){
        return this.bookingService.getByBookingLikeName(name);
    }
    @PostMapping(path = "/getId/{id}")
    DataResult<GetBooking> getById(@PathVariable(name = "id") long id){
        return this.bookingService.getById(id);
    }
    @DeleteMapping(path = "/delete/{id}")
    Result delete(@PathVariable(name = "id") long id){
        return this.bookingService.delete(id);
    }
    @PutMapping(path = "/update/{id}")
    Result updateBooking(@PathVariable(name = "id") long id,
                         @Valid @RequestBody UpdateBookingReq updateBookingReq){
        return this.bookingService.updateBooking(id, updateBookingReq);
    }
    @PostMapping(path = "/create")
    Result createBooking(@Valid @RequestBody CreateBookingReq createBookingReq){
        return this.bookingService.createBooking(createBookingReq);
    }
    @PutMapping(path = "/isDone/{id}")
    Result updateIsDone(@PathVariable(name = "id") long id){
        return this.bookingService.updateIsDone(id);
    }
}
