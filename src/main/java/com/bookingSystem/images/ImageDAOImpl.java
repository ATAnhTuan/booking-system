package com.bookingSystem.images;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ImageDAOImpl implements ImageDAO {
    private final SessionFactory sessionFactory;

    public ImageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Images save(Images image) {
        sessionFactory.getCurrentSession().save(image);
        return image;
    }

    @Override
    public Images update(Images image) {
        sessionFactory.getCurrentSession().update(image);
        return image;
    }

    @Override
    public void delete(Images image) {
        sessionFactory.getCurrentSession().delete(image);
    }

    @Override
    public Optional<Images> findByGuid(UUID guid) {
        Session session = sessionFactory.getCurrentSession();
        Images image = session
                .createQuery("from Images where imageGuid = :guid", Images.class)
                .setParameter("guid", guid)
                .uniqueResult();
        return Optional.ofNullable(image);
    }

    @Override
    public List<Images> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Images", Images.class)
                .list();
    }
}
