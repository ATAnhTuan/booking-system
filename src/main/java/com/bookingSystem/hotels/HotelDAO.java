package com.bookingSystem.hotels;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HotelDAO {
    Hotels save(Hotels hotel);
    Hotels update(Hotels hotel);
    void delete(Hotels hotel);
    Optional<Hotels> findByGuid(UUID guid);
    List<Hotels> findAll();
}
