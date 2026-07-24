package com.bookingSystem.images;

import java.util.UUID;

public class ImageMapper {
    public ImageResponseDTO toResponseDTO(Images image) {
        if (image == null) return null;

        ImageResponseDTO response = new ImageResponseDTO();
        response.setImageGuid(image.getImageGuid());
        response.setHotelGuid(image.getHotelGuid());
        response.setRoomGuid(image.getRoomGuid());
        response.setImageUrl(image.getImageUrl());
        response.setDescription(image.getDescription());
        response.setCreatedAt(image.getCreatedAt());
        return response;
    }

    public Images toEntity(ImageRequestDTO request) {
        if (request == null) return null;

        Images image = new Images();
        image.setImageGuid(UUID.randomUUID());
        updateEntityFromDTO(request, image);
        return image;
    }

    public void updateEntityFromDTO(ImageRequestDTO request, Images image) {
        if (request == null || image == null) return;

        if (request.getHotelGuid() != null) image.setHotelGuid(request.getHotelGuid());
        if (request.getRoomGuid() != null) image.setRoomGuid(request.getRoomGuid());
        if (request.getImageUrl() != null) image.setImageUrl(request.getImageUrl());
        if (request.getDescription() != null) image.setDescription(request.getDescription());
    }
}
