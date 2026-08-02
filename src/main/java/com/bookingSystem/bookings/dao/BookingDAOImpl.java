package com.bookingSystem.bookings.dao;

import com.bookingSystem.bookings.Booking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BookingDAOImpl implements BookingDAO {
    private final SessionFactory sessionFactory;

    public BookingDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Booking save(Booking booking) {
        sessionFactory.getCurrentSession().save(booking);
        return booking;
    }

    @Override
    public Booking update(Booking booking) {
        sessionFactory.getCurrentSession().update(booking);
        return booking;
    }

    @Override
    public void delete(Booking booking) {
        sessionFactory.getCurrentSession().delete(booking);
    }

    @Override
    public Optional<Booking> findByGuid(UUID guid) {
        Session session = sessionFactory.getCurrentSession();
        Booking booking = session
                .createQuery("from Booking where bookingGuid = :guid", Booking.class)
                .setParameter("guid", guid)
                .uniqueResult();
        return Optional.ofNullable(booking);
    }

    @Override
    public List<Booking> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Booking", Booking.class)
                .list();
    }
}
