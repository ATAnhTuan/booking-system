package com.bookingSystem.repository;

import com.bookingSystem.entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserRepository extends BaseRepository<UsersEntity,String>{

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        super( jdbcTemplate,"users", new BeanPropertyRowMapper<>(UsersEntity.class));
    }

    @Override
    public List<UsersEntity> findAll() {
        String sql = "SELECT * FROM " + tableName + " WHERE is_active = true";
        return super.jdbcTemplate.query(sql, rowMapper);
    }


    @Override
    public String save(UsersEntity entity) {
        String sql = "INSERT INTO users (user_guid,username, password, email, phone, role, member_rank) "
                + "VALUES (?,?, ?, ?, ?, ?, ?) RETURNING guid";
        return jdbcTemplate.queryForObject(sql, String.class,
                entity.getUserGuid(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getRole(),
                entity.getMemberRank()
        );
    }
    @Override
    public int update(UsersEntity entity) {
        return 0;
    }
}
