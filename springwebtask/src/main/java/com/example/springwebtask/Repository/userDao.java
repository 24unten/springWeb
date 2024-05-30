package com.example.springwebtask.Repository;

import com.example.springwebtask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.*;

@Repository
public class userDao implements PgUserDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

//    @Override
    public User findById(String id, String password) {
        var param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("password",password);
        var list = jdbcTemplate.query("SELECT * FROM users WHERE login_id = :id and password = :password", param, new DataClassRowMapper<>(User.class));
        return list.isEmpty() ? null : list.get(0);
    }
}
