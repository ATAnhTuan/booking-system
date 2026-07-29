package com.bookingSystem.rooms;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomDAO {
    Rooms save(Rooms room);
    Rooms update(Rooms room);
    void delete(Rooms room);
    Optional<Rooms> findByGuid(UUID guid);
    List<Rooms> findAll();
}
