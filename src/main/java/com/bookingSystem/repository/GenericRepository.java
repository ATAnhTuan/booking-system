package com.bookingSystem.repository;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    ID save(T entity);
    int update(T entity);
    int deleteById(ID id);
}