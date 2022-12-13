package com.example.technicalservice.service.concretes;

import com.example.technicalservice.core.results.DataResult;
import com.example.technicalservice.core.results.Result;
import com.example.technicalservice.core.results.SuccessDataResult;
import com.example.technicalservice.core.results.SuccessResult;
import com.example.technicalservice.dataAccess.BookingRepository;
import com.example.technicalservice.dto.booking.requests.CreateBookingReq;
import com.example.technicalservice.dto.booking.requests.UpdateBookingReq;
import com.example.technicalservice.dto.booking.responses.BookingGetAllResponse;
import com.example.technicalservice.dto.booking.responses.BookingGetResponse;
import com.example.technicalservice.model.Booking;
import com.example.technicalservice.service.abstracts.BookingService;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingManager implements BookingService {

    private final BookingRepository bookingRepository;
    private final EntityManager manager;

    @Override
    public DataResult<List<BookingGetAllResponse>> getAllBookingAsc() {
        List<Booking> allByOrderByBookingDateAsc = this.bookingRepository.findAllByOrderByBookingDateAsc();
        List<BookingGetAllResponse> getAllBookings = new ArrayList<>();
        for (Booking booking : allByOrderByBookingDateAsc) {
            BookingGetAllResponse getAllBooking = new BookingGetAllResponse();
            getAllBooking.setBookingDate(booking.getBookingDate());
            getAllBooking.setUser(booking.getUser());
            getAllBooking.setId(booking.getId());
            getAllBooking.setDone(booking.isDone());
            getAllBooking.setNote(booking.getNote());
            getAllBooking.setService(booking.getService());
            getAllBookings.add(getAllBooking);
        }
        return new SuccessDataResult<>(getAllBookings,"Bookings are Successfully Listed Asc");
    }

    @Override
    public DataResult<List<BookingGetAllResponse>> getAllBookingDesc() {
        List<Booking> allByOrderByBookingDateAsc = this.bookingRepository.findAllByOrderByBookingDateDesc();
        List<BookingGetAllResponse> getAllBookings = new ArrayList<>();
        for (Booking booking : allByOrderByBookingDateAsc) {
            BookingGetAllResponse getAllBooking = new BookingGetAllResponse();
            getAllBooking.setBookingDate(booking.getBookingDate());
            getAllBooking.setUser(booking.getUser());
            getAllBooking.setId(booking.getId());
            getAllBooking.setDone(booking.isDone());
            getAllBooking.setNote(booking.getNote());
            getAllBooking.setService(booking.getService());
            getAllBookings.add(getAllBooking);
        }
        return new SuccessDataResult<>(getAllBookings,"Bookings are Successfully Listed Desc");
    }


    @Override
    public DataResult<List<BookingGetAllResponse>> getAllBookingDateAsc(LocalDate date) {
        List<Booking> bookings = this.bookingRepository.findByBookingDateOrderByBookingDateAsc(date);
        List<BookingGetAllResponse> getAllBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingGetAllResponse getAllBooking = new BookingGetAllResponse();
            getAllBooking.setBookingDate(booking.getBookingDate());
            getAllBooking.setUser(booking.getUser());
            getAllBooking.setId(booking.getId());
            getAllBooking.setDone(booking.isDone());
            getAllBooking.setNote(booking.getNote());
            getAllBooking.setService(booking.getService());
            getAllBookings.add(getAllBooking);
        }
        return new SuccessDataResult<>(getAllBookings,"Bookings are Successfully Listed! By Date Asc!");
    }

    @Override
    public DataResult<List<BookingGetAllResponse>> getAllBookingDateDesc(LocalDate date) {
        List<Booking> bookings = this.bookingRepository.findByBookingDateOrderByBookingDateDesc(date);
        List<BookingGetAllResponse> getAllBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingGetAllResponse getAllBooking = new BookingGetAllResponse();
            getAllBooking.setBookingDate(booking.getBookingDate());
            getAllBooking.setUser(booking.getUser());
            getAllBooking.setId(booking.getId());
            getAllBooking.setDone(booking.isDone());
            getAllBooking.setNote(booking.getNote());
            getAllBooking.setService(booking.getService());
            getAllBookings.add(getAllBooking);
        }
        return new SuccessDataResult<>(getAllBookings,"Bookings are Successfully Listed By Date Desc!");
    }

    @Override
    public DataResult<List<BookingGetResponse>> getByBookingLikeName(String name) {
        List<Booking> bookings = this.bookingRepository.findByBookingLikeName(name);
        List<BookingGetResponse> bookingGetResponses = new ArrayList<>();
        for (Booking booking : bookings) {
            BookingGetResponse bookingGetResponse = new BookingGetResponse();
            bookingGetResponse.setBookingDate(booking.getBookingDate());
            bookingGetResponse.setDone(booking.isDone());
            bookingGetResponse.setUser(booking.getUser());
            bookingGetResponse.setNote(booking.getNote());
            bookingGetResponse.setService(booking.getService());
            bookingGetResponses.add(bookingGetResponse);
        }
        return new SuccessDataResult<>(bookingGetResponses,"Bookings are Successfully " +
                "Listed! By UserName");
    }

    @Override
    public DataResult<BookingGetResponse> getById(long id) {
        Optional<Booking> byId = this.bookingRepository.findById(id);
        BookingGetResponse bookingGetResponse = new BookingGetResponse();
        byId.stream()
                .map(booking -> {
                    bookingGetResponse.setNote(booking.getNote());
                    bookingGetResponse.setBookingDate(booking.getBookingDate());
                    bookingGetResponse.setDone(booking.isDone());
                    bookingGetResponse.setUser(booking.getUser());
                    bookingGetResponse.setService(booking.getService());
                    return bookingGetResponse;
                });
        return new SuccessDataResult<>(bookingGetResponse,"Booking is Successfully getting!");
    }

    @Override
    public Result delete(long id) {
        this.bookingRepository.deleteById(id);
        return new SuccessResult(id+".Number Successfully Deleted!");
    }

    @Override
    public Result updateBooking(long id, UpdateBookingReq updateBookingReq) {
        Booking booking = this.bookingRepository.findById(id).get();
        booking.setBookingDate(updateBookingReq.getBookingDate());
        booking.setDone(updateBookingReq.isDone());
        booking.setUser(updateBookingReq.getUser());
        booking.setService(updateBookingReq.getService());
        booking.setNote(updateBookingReq.getNote());
        booking.setService(updateBookingReq.getService());
        return new SuccessResult(id+".Number Successfully Updated!!");
    }

    @Override
    public Result createBooking(CreateBookingReq createBookingReq) {
        Booking booking = new Booking();
        booking.setDone(createBookingReq.isDone());
        booking.setUser(createBookingReq.getUser());
        booking.setBookingDate(createBookingReq.getBookingDate());
        booking.setNote(createBookingReq.getNote());
        booking.setService(createBookingReq.getService());
        LocalDate date = createBookingDate(booking.getService().getDuration());
        booking.setBookingDate(date);
        this.bookingRepository.saveAndFlush(booking);
        manager.clear();
        booking=this.bookingRepository.findById(booking.getId()).get();

        return new SuccessResult("Booking is Successfully created!!");
    }

    @Override
    public Result updateIsDone(long id) {
        Booking booking = this.bookingRepository.findById(id).get();
        booking.setDone(true);
        this.bookingRepository.save(booking);
        return new SuccessResult("isDone is Successfully updated !");
    }

    private LocalDate createBookingDate(int serviceDuration)
    {
        LocalDate bookingDate = LocalDate.now();
        int dailyWorkHours = 0;
        do
        {
            dailyWorkHours = serviceDuration;
            bookingDate = bookingDate.plusDays(1L);
            List<Booking> bookings = this.bookingRepository.searchByBookingDate(bookingDate);
            for (Booking booking : bookings)
            {
                dailyWorkHours += booking.getService().getDuration();
            }
        }
        while (dailyWorkHours > 10);
        return bookingDate;
    }

    public void refresh(Booking booking)
    {
        manager.refresh(booking);
    }
}
