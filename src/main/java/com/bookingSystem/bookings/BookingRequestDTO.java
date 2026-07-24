package com.bookingSystem.bookings;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class BookingRequestDTO {
    @NotNull(message = "Hotel guid is required")
    private UUID hotelGuid;

    @NotNull(message = "Room guid is required")
    private UUID roomGuid;

    @NotNull(message = "User guid is required")
    private UUID userGuid;

    @Pattern(regexp = "^(0|\\+84)[3|5|7|8|9][0-9]{8}$", message = "Contact phone is invalid")
    private String contactPhone;

    @Email(message = "Contact email is invalid")
    private String contactEmail;

    private String description;

    @NotNull(message = "Booking start date is required")
    private LocalDate bookingDateStart;

    @NotNull(message = "Booking end date is required")
    private LocalDate bookingDateEnd;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    private BookingStatus status;

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

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }
}
