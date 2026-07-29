package com.bookingSystem.hotels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class HotelDAOImpl implements HotelDAO {
    private final SessionFactory sessionFactory;

    public HotelDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Hotels save(Hotels hotel) {
        sessionFactory.getCurrentSession().save(hotel);
        return hotel;
    }

    @Override
    public Hotels update(Hotels hotel) {
        sessionFactory.getCurrentSession().update(hotel);
        return hotel;
    }

    @Override
    public void delete(Hotels hotel) {
        sessionFactory.getCurrentSession().delete(hotel);
    }

    @Override
    public Optional<Hotels> findByGuid(UUID guid) {
        Session session = sessionFactory.getCurrentSession();
        Hotels hotel = session
                .createQuery("from Hotels where hotelGuid = :guid", Hotels.class)
                .setParameter("guid", guid)
                .uniqueResult();
        return Optional.ofNullable(hotel);
    }

    @Override
    public List<Hotels> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Hotels", Hotels.class)
                .list();
    }
}
