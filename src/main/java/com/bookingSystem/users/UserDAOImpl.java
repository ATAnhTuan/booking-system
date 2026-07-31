package com.bookingSystem.users;

import com.bookingSystem.exception.ResourceNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;

    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User save(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public User update(User user) {
        sessionFactory.getCurrentSession().update(user);
        return user;
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public void deactivate(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public Optional<User> findByGuid(UUID guid) {
        Session session = sessionFactory.getCurrentSession();
        User user = session
                .createQuery("from User where userGuid = :guid", User.class)
                .setParameter("guid", guid)
                .uniqueResult();
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from User where active = ?1 and username <> ?2", User.class)
                .setParameter(1, true)
                .setParameter(2, "admin")
                .list();
    }
}
