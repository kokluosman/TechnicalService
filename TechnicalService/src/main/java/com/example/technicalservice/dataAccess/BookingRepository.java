package com.example.technicalservice.dataAccess;

import com.example.technicalservice.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    List<Booking> findAllByOrderByBookingDateAsc();
    List<Booking> findAllByOrderByBookingDateDesc();

    List<Booking> findByBookingDateOrderByBookingDateAsc(LocalDate date);
    List<Booking> findByBookingDateOrderByBookingDateDesc(LocalDate date);

    @Query(name = "findByBookingLikeName", value = "SELECT p FROM Booking p WHERE p.user.name LIKE %:NAME%")
    public List<Booking> findByBookingLikeName(@Param("NAME") String name);

    List<Booking> searchByBookingDate(LocalDate bookingDate);

}
