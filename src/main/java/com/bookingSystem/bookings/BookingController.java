package com.bookingSystem.bookings;

import com.bookingSystem.auth.RequireRoles;
import com.bookingSystem.bookings.dto.BookingRequestDTO;
import com.bookingSystem.bookings.dto.BookingResponseDTO;
import com.bookingSystem.exception.ApiResponse;
import com.bookingSystem.users.userEnum.UserRole;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@Api(tags = "Booking Management")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/{guid}")
    @RequireRoles
    public ResponseEntity<ApiResponse<BookingResponseDTO>> getBooking(@PathVariable UUID guid) {
        return ResponseEntity.ok(ApiResponse.success(bookingService.getByGuid(guid), "Booking found successfully"));
    }

    @GetMapping
    @RequireRoles({UserRole.ADMIN, UserRole.MANAGER, UserRole.STAFF})
    public ResponseEntity<ApiResponse<List<BookingResponseDTO>>> getBookings() {
        return ResponseEntity.ok(ApiResponse.success(bookingService.getAllBookings(), "Bookings found successfully"));
    }

    @PostMapping
    @RequireRoles
    public ResponseEntity<ApiResponse<BookingResponseDTO>> createBooking(@Valid @RequestBody BookingRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created(bookingService.createBooking(request), "Create booking success"));
    }

    @PutMapping("/{guid}")
    @RequireRoles({UserRole.ADMIN, UserRole.MANAGER, UserRole.STAFF})
    public ResponseEntity<ApiResponse<BookingResponseDTO>> updateBooking(
            @PathVariable UUID guid,
            @Valid @RequestBody BookingRequestDTO request) {
        return ResponseEntity.ok(ApiResponse.success(bookingService.updateBooking(guid, request), "Booking updated success"));
    }

    @DeleteMapping("/{guid}")
    @RequireRoles({UserRole.ADMIN, UserRole.MANAGER})
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID guid) {
        bookingService.deleteBooking(guid);
        return ResponseEntity.noContent().build();
    }
}
