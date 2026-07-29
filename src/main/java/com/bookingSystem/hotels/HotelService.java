package com.bookingSystem.hotels;

import com.bookingSystem.exception.ResourceNotFoundException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class HotelService {
    private final HotelDAO hotelDAO;
    private final HotelMapper hotelMapper;

    public HotelService(HotelDAO hotelDAO, HotelMapper hotelMapper) {
        this.hotelDAO = hotelDAO;
        this.hotelMapper = hotelMapper;
    }

    @Transactional
    public HotelResponseDTO getByGuid(UUID guid) {
        return hotelMapper.toResponseDTO(findHotelByGuid(guid));
    }

    @Transactional
    public List<HotelResponseDTO> getAllHotels() {
        return hotelDAO.findAll()
                .stream()
                .map(hotelMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public HotelResponseDTO createHotel(HotelRequestDTO request) {
        Hotels hotel = hotelMapper.toEntity(request);
        return hotelMapper.toResponseDTO(hotelDAO.save(hotel));
    }

    @Transactional
    public HotelResponseDTO updateHotel(UUID guid, HotelRequestDTO request) {
        Hotels hotel = findHotelByGuid(guid);
        hotelMapper.updateEntityFromDTO(request, hotel);
        return hotelMapper.toResponseDTO(hotelDAO.update(hotel));
    }

    @Transactional
    public void deleteHotel(UUID guid) {
        hotelDAO.delete(findHotelByGuid(guid));
    }

    private Hotels findHotelByGuid(UUID guid) {
        return hotelDAO.findByGuid(guid)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found: " + guid));
    }
}
