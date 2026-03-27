package com.bookingSystem.entity;

import java.util.UUID;

public class Hotels {
    private Long id;
    private UUID hotelGuid;
    private String hotelName;
    private String address;
    private String description;
    private String phone;
    private String email;
    private String status;
    private String rating;

    public Hotels(String hotelName, String address, String description, String phone, String email, String status, String rating) {
        this.hotelName = hotelName;
        this.address = address;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.rating = rating;
    }

    public Hotels() {
    }

    public UUID getHotelGuid() {
        return hotelGuid;
    }

    public void setHotelGuid(UUID hotelGuid) {
        this.hotelGuid = hotelGuid;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
