package com.bookingSystem.hotels;

import com.bookingSystem.exception.ApiResponse;
import com.bookingSystem.hotels.dto.HotelRequestDTO;
import com.bookingSystem.hotels.dto.HotelResponseDTO;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/hotels")
@Api(tags = "Hotel Management")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/{guid}")
    public ResponseEntity<ApiResponse<HotelResponseDTO>> getHotel(@PathVariable UUID guid) {
        return ResponseEntity.ok(ApiResponse.success(hotelService.getByGuid(guid), "Hotel found successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<HotelResponseDTO>>> getHotels() {
        return ResponseEntity.ok(ApiResponse.success(hotelService.getAllHotels(), "Hotels found successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<HotelResponseDTO>> createHotel(@Valid @RequestBody HotelRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(hotelService.createHotel(request), "Create hotel success"));
    }

    @PutMapping("/{guid}")
    public ResponseEntity<ApiResponse<HotelResponseDTO>> updateHotel(
            @PathVariable UUID guid,
            @Valid @RequestBody HotelRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success(hotelService.updateHotel(guid, request), "Hotel updated success"));
    }

    @DeleteMapping("/{guid}")
    public ResponseEntity<Void> deleteHotel(@PathVariable UUID guid) {
        hotelService.deleteHotel(guid);
        return ResponseEntity.noContent().build();
    }
}
