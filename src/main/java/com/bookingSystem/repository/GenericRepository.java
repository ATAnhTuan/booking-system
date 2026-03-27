package com.bookingSystem.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    List<T> findAll();
    T findById(ID id);
    int save(T entity);
    int update(T entity);
    int deleteById(ID id);
}