package com.bookingSystem.rooms.dto;

import com.bookingSystem.rooms.roomEnum.RoomStatus;
import com.bookingSystem.rooms.Rooms;

import java.util.UUID;

public class RoomMapper {
    public RoomResponseDTO toResponseDTO(Rooms room) {
        if (room == null) return null;

        RoomResponseDTO response = new RoomResponseDTO();
        response.setRoomGuid(room.getRoomGuid());
        response.setHotelGuid(room.getHotelGuid());
        response.setRoomName(room.getRoomName());
        response.setDescription(room.getDescription());
        response.setCategory(room.getCategory());
        response.setStatus(room.getStatus());
        response.setPrice(room.getPrice());
        response.setCreatedAt(room.getCreatedAt());
        response.setUpdatedAt(room.getUpdatedAt());
        return response;
    }

    public Rooms toEntity(RoomRequestDTO request) {
        if (request == null) return null;

        Rooms room = new Rooms();
        room.setRoomGuid(UUID.randomUUID());
        updateEntityFromDTO(request, room);
        if (room.getStatus() == null) room.setStatus(RoomStatus.AVAILABLE);
        return room;
    }

    public void updateEntityFromDTO(RoomRequestDTO request, Rooms room) {
        if (request == null || room == null) return;

        if (request.getHotelGuid() != null) room.setHotelGuid(request.getHotelGuid());
        if (request.getRoomName() != null) room.setRoomName(request.getRoomName());
        if (request.getDescription() != null) room.setDescription(request.getDescription());
        if (request.getCategory() != null) room.setCategory(request.getCategory());
        if (request.getStatus() != null) room.setStatus(request.getStatus());
        if (request.getPrice() != null) room.setPrice(request.getPrice());
    }
}
