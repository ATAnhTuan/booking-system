package com.bookingSystem.bookings;

import com.bookingSystem.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDAO bookingDAO;
    private final BookingMapper bookingMapper;

    public BookingService(BookingDAO bookingDAO, BookingMapper bookingMapper) {
        this.bookingDAO = bookingDAO;
        this.bookingMapper = bookingMapper;
    }

    @Transactional
    public BookingResponseDTO getByGuid(UUID guid) {
        return bookingMapper.toResponseDTO(findBookingByGuid(guid));
    }

    @Transactional
    public List<BookingResponseDTO> getAllBookings() {
        return bookingDAO.findAll()
                .stream()
                .map(bookingMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        Booking booking = bookingMapper.toEntity(request);
        return bookingMapper.toResponseDTO(bookingDAO.save(booking));
    }

    @Transactional
    public BookingResponseDTO updateBooking(UUID guid, BookingRequestDTO request) {
        Booking booking = findBookingByGuid(guid);
        bookingMapper.updateEntityFromDTO(request, booking);
        return bookingMapper.toResponseDTO(bookingDAO.update(booking));
    }

    @Transactional
    public void deleteBooking(UUID guid) {
        bookingDAO.delete(findBookingByGuid(guid));
    }

    private Booking findBookingByGuid(UUID guid) {
        return bookingDAO.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found: " + guid));
    }
}
