package com.bookingSystem.users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserEntity save(UserEntity user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public UserEntity update(UserEntity user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }

    @Override
    public void delete(UserEntity user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public Optional<UserEntity> findByGuid(UUID guid) {
        Session session = sessionFactory.getCurrentSession();
        UserEntity user = session
                .createQuery("from UserEntity where userGuid = :guid", UserEntity.class)
                .setParameter("guid", guid)
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from UserEntity", UserEntity.class)
                .list();
    }
}
