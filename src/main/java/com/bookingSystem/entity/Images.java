package com.bookingSystem.entity;

import java.util.UUID;

public class Images {
    private Long id;
    private UUID hotelGuid;
    private UUID roomsGuid;
    private String image;

    public Images(UUID hotelGuid, UUID roomsGuid, String image) {
        this.hotelGuid = hotelGuid;
        this.roomsGuid = roomsGuid;
        this.image = image;
    }

    public Images() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getHotelGuid() {
        return hotelGuid;
    }

    public void setHotelGuid(UUID hotelGuid) {
        this.hotelGuid = hotelGuid;
    }

    public UUID getRoomsGuid() {
        return roomsGuid;
    }

    public void setRoomsGuid(UUID roomsGuid) {
        this.roomsGuid = roomsGuid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
