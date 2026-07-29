package com.bookingSystem.rooms;

import com.bookingSystem.exception.ApiResponse;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rooms")
@Api(tags = "Room Management")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<ApiResponse<RoomResponseDTO>> getRoom(@PathVariable UUID guid) {
        return ResponseEntity.ok(ApiResponse.success(roomService.getByGuid(guid), "Room found successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoomResponseDTO>>> getRooms() {
        return ResponseEntity.ok(ApiResponse.success(roomService.getAllRooms(), "Rooms found successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoomResponseDTO>> createRoom(@Valid @RequestBody RoomRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(roomService.createRoom(request), "Create room success"));
    }

    @PutMapping("/{guid}")
    public ResponseEntity<ApiResponse<RoomResponseDTO>> updateRoom(
            @PathVariable UUID guid,
            @Valid @RequestBody RoomRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success(roomService.updateRoom(guid, request), "Room updated success"));
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteRoom(@PathVariable UUID guid) {
        roomService.deleteRoom(guid);
        return ResponseEntity.noContent().build();
    }
}
