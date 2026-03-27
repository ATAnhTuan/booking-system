package com.bookingSystem.entity;

import java.util.Date;
import java.util.UUID;

public class Booking {
    private Long id;
    private UUID hotelGuid;
    private UUID roomGuid;
    private UUID bookingGuid;
    private UUID userGuid;
    private String contactPhone;
    private String contactEmail;
    private String description;
    private Date bookingDateStart;
    private Date bookingDateEnd;
    private Long price;

    public Booking(UUID hotelGuid, UUID roomGuid, UUID userGuid, String contactPhone, String contactEmail, String description, Date bookingDateStart, Date bookingDateEnd, Long price) {
        this.hotelGuid = hotelGuid;
        this.roomGuid = roomGuid;
        this.userGuid = userGuid;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.description = description;
        this.bookingDateStart = bookingDateStart;
        this.bookingDateEnd = bookingDateEnd;
        this.price = price;
    }

    public Booking() {
    }

    public UUID getHotelGuid() {
        return hotelGuid;
    }

    public void setHotelGuid(UUID hotelGuid) {
        this.hotelGuid = hotelGuid;
    }

    public UUID getRoomGuid() {
        return roomGuid;
    }

    public void setRoomGuid(UUID roomGuid) {
        this.roomGuid = roomGuid;
    }

    public UUID getBookingGuid() {
        return bookingGuid;
    }

    public void setBookingGuid(UUID bookingGuid) {
        this.bookingGuid = bookingGuid;
    }

    public UUID getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(UUID userGuid) {
        this.userGuid = userGuid;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBookingDateStart() {
        return bookingDateStart;
    }

    public void setBookingDateStart(Date bookingDateStart) {
        this.bookingDateStart = bookingDateStart;
    }

    public Date getBookingDateEnd() {
        return bookingDateEnd;
    }

    public void setBookingDateEnd(Date bookingDateEnd) {
        this.bookingDateEnd = bookingDateEnd;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
