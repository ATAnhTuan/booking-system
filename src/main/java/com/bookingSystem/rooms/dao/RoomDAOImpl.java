package com.bookingSystem.rooms.dao;

import com.bookingSystem.rooms.Rooms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoomDAOImpl implements RoomDAO {
    private final SessionFactory sessionFactory;

    public RoomDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rooms save(Rooms room) {
        sessionFactory.getCurrentSession().save(room);
        return room;
    }

    @Override
    public Rooms update(Rooms room) {
        sessionFactory.getCurrentSession().update(room);
        return room;
    }

    @Override
    public void delete(Rooms room) {
        sessionFactory.getCurrentSession().delete(room);
    }

    @Override
    public Optional<Rooms> findByGuid(UUID guid) {
        Session session = sessionFactory.getCurrentSession();
        Rooms room = session
                .createQuery("from Rooms where roomGuid = :guid", Rooms.class)
                .setParameter("guid", guid)
                .uniqueResult();
        return Optional.ofNullable(room);
    }

    @Override
    public List<Rooms> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Rooms", Rooms.class)
                .list();
    }
}
