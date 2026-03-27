package com.bookingSystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;


public abstract class BaseRepository<T, ID> implements GenericRepository<T, ID> {

    protected final JdbcTemplate jdbcTemplate;
    protected final String tableName;
    protected final RowMapper<T> rowMapper;

    public BaseRepository(JdbcTemplate jdbcTemplate, String tableName, RowMapper<T> rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.tableName = tableName;
        this.rowMapper = rowMapper;
    }


    @Override
    public List<T> findAll() {
        String sql = "SELECT * FROM " + tableName; // Lưu ý: Cần thận trọng với SQL Injection nếu tableName đến từ bên ngoài
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public T findById(ID id) {
        String sql = "SELECT * FROM " + tableName + " WHERE guid = ?::uuid";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public int deleteById(ID id) {
        String sql = "DELETE * FROM " + tableName + " WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

}
