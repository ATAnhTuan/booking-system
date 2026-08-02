package com.bookingSystem.hotels.dto;

import com.bookingSystem.hotels.hotelEnum.HotelStatus;
import com.bookingSystem.hotels.Hotels;

import java.util.UUID;

public class HotelMapper {
    public HotelResponseDTO toResponseDTO(Hotels hotel) {
        if (hotel == null) return null;

        HotelResponseDTO response = new HotelResponseDTO();
        response.setHotelGuid(hotel.getHotelGuid());
        response.setHotelName(hotel.getHotelName());
        response.setAddress(hotel.getAddress());
        response.setDescription(hotel.getDescription());
        response.setPhone(hotel.getPhone());
        response.setEmail(hotel.getEmail());
        response.setStatus(hotel.getStatus());
        response.setRating(hotel.getRating());
        response.setCreatedAt(hotel.getCreatedAt());
        response.setUpdatedAt(hotel.getUpdatedAt());
        return response;
    }

    public Hotels toEntity(HotelRequestDTO request) {
        if (request == null) return null;

        Hotels hotel = new Hotels();
        hotel.setHotelGuid(UUID.randomUUID());
        updateEntityFromDTO(request, hotel);
        if (hotel.getStatus() == null) hotel.setStatus(HotelStatus.ACTIVE);
        return hotel;
    }

    public void updateEntityFromDTO(HotelRequestDTO request, Hotels hotel) {
        if (request == null || hotel == null) return;

        if (request.getHotelName() != null) hotel.setHotelName(request.getHotelName());
        if (request.getAddress() != null) hotel.setAddress(request.getAddress());
        if (request.getDescription() != null) hotel.setDescription(request.getDescription());
        if (request.getPhone() != null) hotel.setPhone(request.getPhone());
        if (request.getEmail() != null) hotel.setEmail(request.getEmail());
        if (request.getStatus() != null) hotel.setStatus(request.getStatus());
        if (request.getRating() != null) hotel.setRating(request.getRating());
    }
}
