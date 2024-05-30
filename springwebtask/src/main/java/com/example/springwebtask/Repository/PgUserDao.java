package com.example.springwebtask.Repository;


import com.example.springwebtask.entity.User;

public interface PgUserDao {

    User findById(String id, String password);
}
