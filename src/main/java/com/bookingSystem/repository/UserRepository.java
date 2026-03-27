package com.bookingSystem.repository;

import com.bookingSystem.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository extends BaseRepository<Users,String>{

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        super( jdbcTemplate,"users", new BeanPropertyRowMapper<>(Users.class));
    }

    @Override
    public List<Users> findAll() {
        String sql = "SELECT * FROM " + tableName + " WHERE is_active = true";
        return super.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public Users findById(String guid) {
        return super.findById(guid);
    }

    @Override
    public int save(Users entity) {
        return 0;
    }

    @Override
    public int update(Users entity) {
        return 0;
    }
}
