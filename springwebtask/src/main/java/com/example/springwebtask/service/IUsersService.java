package com.example.springwebtask.service;


import com.example.springwebtask.entity.User;

public interface IUsersService {

    User findById(String id, String password);
}
