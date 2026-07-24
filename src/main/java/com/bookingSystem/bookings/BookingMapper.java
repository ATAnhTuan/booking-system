package com.bookingSystem.bookings;

import java.util.UUID;

public class BookingMapper {
    public BookingResponseDTO toResponseDTO(Booking booking) {
        if (booking == null) return null;

        BookingResponseDTO response = new BookingResponseDTO();
        response.setBookingGuid(booking.getBookingGuid());
        response.setHotelGuid(booking.getHotelGuid());
        response.setRoomGuid(booking.getRoomGuid());
        response.setUserGuid(booking.getUserGuid());
        response.setContactPhone(booking.getContactPhone());
        response.setContactEmail(booking.getContactEmail());
        response.setDescription(booking.getDescription());
        response.setBookingDateStart(booking.getBookingDateStart());
        response.setBookingDateEnd(booking.getBookingDateEnd());
        response.setPrice(booking.getPrice());
        response.setStatus(booking.getStatus());
        response.setCreatedAt(booking.getCreatedAt());
        response.setUpdatedAt(booking.getUpdatedAt());
        return response;
    }

    public Booking toEntity(BookingRequestDTO request) {
        if (request == null) return null;

        Booking booking = new Booking();
        booking.setBookingGuid(UUID.randomUUID());
        updateEntityFromDTO(request, booking);
        if (booking.getStatus() == null) booking.setStatus(BookingStatus.PENDING);
        return booking;
    }

    public void updateEntityFromDTO(BookingRequestDTO request, Booking booking) {
        if (request == null || booking == null) return;

        if (request.getHotelGuid() != null) booking.setHotelGuid(request.getHotelGuid());
        if (request.getRoomGuid() != null) booking.setRoomGuid(request.getRoomGuid());
        if (request.getUserGuid() != null) booking.setUserGuid(request.getUserGuid());
        if (request.getContactPhone() != null) booking.setContactPhone(request.getContactPhone());
        if (request.getContactEmail() != null) booking.setContactEmail(request.getContactEmail());
        if (request.getDescription() != null) booking.setDescription(request.getDescription());
        if (request.getBookingDateStart() != null) booking.setBookingDateStart(request.getBookingDateStart());
        if (request.getBookingDateEnd() != null) booking.setBookingDateEnd(request.getBookingDateEnd());
        if (request.getPrice() != null) booking.setPrice(request.getPrice());
        if (request.getStatus() != null) booking.setStatus(request.getStatus());
    }
}
