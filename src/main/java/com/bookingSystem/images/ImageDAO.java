package com.bookingSystem.images;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ImageDAO {
    Images save(Images image);
    Images update(Images image);
    void delete(Images image);
    Optional<Images> findByGuid(UUID guid);
    List<Images> findAll();
}
