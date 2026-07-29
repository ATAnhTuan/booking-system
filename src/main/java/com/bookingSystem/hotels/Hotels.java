package com.bookingSystem.hotels;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "hotels")
public class Hotels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hotel_guid", unique = true, nullable = false)
    private UUID hotelGuid;

    @Column(name = "hotel_name")
    private String hotelName;

    private String address;
    private String description;
    private String phone;
    private String email;

    @Enumerated(EnumType.STRING)
    private HotelStatus status = HotelStatus.ACTIVE;

    private String rating;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Hotels(String hotelName, String address, String description, String phone, String email, HotelStatus status, String rating) {
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

    public HotelStatus getStatus() {
        return status;
    }

    public void setStatus(HotelStatus status) {
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
