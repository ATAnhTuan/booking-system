package com.bookingSystem.bookings;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_guid")
    private UUID hotelGuid;

    @Column(name = "room_guid")
    private UUID roomGuid;

    @Column(name = "booking_guid", unique = true, nullable = false)
    private UUID bookingGuid;

    @Column(name = "user_guid")
    private UUID userGuid;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    private String description;

    @Column(name = "booking_date_start")
    private LocalDate bookingDateStart;

    @Column(name = "booking_date_end")
    private LocalDate bookingDateEnd;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private BookingStatus status = BookingStatus.PENDING;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Booking(UUID hotelGuid, UUID roomGuid, UUID userGuid, String contactPhone, String contactEmail, String description, LocalDate bookingDateStart, LocalDate bookingDateEnd, BigDecimal price) {
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

    public LocalDate getBookingDateStart() {
        return bookingDateStart;
    }

    public void setBookingDateStart(LocalDate bookingDateStart) {
        this.bookingDateStart = bookingDateStart;
    }

    public LocalDate getBookingDateEnd() {
        return bookingDateEnd;
    }

    public void setBookingDateEnd(LocalDate bookingDateEnd) {
        this.bookingDateEnd = bookingDateEnd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
