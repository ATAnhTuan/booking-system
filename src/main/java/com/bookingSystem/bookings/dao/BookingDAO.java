package com.bookingSystem.bookings.dao;

import com.bookingSystem.bookings.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingDAO {
    Booking save(Booking booking);
    Booking update(Booking booking);
    void delete(Booking booking);
    Optional<Booking> findByGuid(UUID guid);
    List<Booking> findAll();
}
