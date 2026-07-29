package com.bookingSystem.rooms;

import com.bookingSystem.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RoomService {
    private final RoomDAO roomDAO;
    private final RoomMapper roomMapper;

    public RoomService(RoomDAO roomDAO, RoomMapper roomMapper) {
        this.roomDAO = roomDAO;
        this.roomMapper = roomMapper;
    }

    @Transactional
    public RoomResponseDTO getByGuid(UUID guid) {
        return roomMapper.toResponseDTO(findRoomByGuid(guid));
    }

    @Transactional
    public List<RoomResponseDTO> getAllRooms() {
        return roomDAO.findAll()
                .stream()
                .map(roomMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RoomResponseDTO createRoom(RoomRequestDTO request) {
        Rooms room = roomMapper.toEntity(request);
        return roomMapper.toResponseDTO(roomDAO.save(room));
    }

    @Transactional
    public RoomResponseDTO updateRoom(UUID guid, RoomRequestDTO request) {
        Rooms room = findRoomByGuid(guid);
        roomMapper.updateEntityFromDTO(request, room);
        return roomMapper.toResponseDTO(roomDAO.update(room));
    }

    @Transactional
    public void deleteRoom(UUID guid) {
        roomDAO.delete(findRoomByGuid(guid));
    }

    private Rooms findRoomByGuid(UUID guid) {
        return roomDAO.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found: " + guid));
    }
}
