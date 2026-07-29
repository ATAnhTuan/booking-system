package com.bookingSystem.images;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_guid", unique = true, nullable = false)
    private UUID imageGuid;

    @Column(name = "hotel_guid")
    private UUID hotelGuid;

    @Column(name = "room_guid")
    private UUID roomGuid;

    @Column(name = "image_url")
    private String imageUrl;

    private String description;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Images(UUID hotelGuid, UUID roomGuid, String imageUrl) {
        this.hotelGuid = hotelGuid;
        this.roomGuid = roomGuid;
        this.imageUrl = imageUrl;
    }

    public Images() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getImageGuid() {
        return imageGuid;
    }

    public void setImageGuid(UUID imageGuid) {
        this.imageGuid = imageGuid;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
