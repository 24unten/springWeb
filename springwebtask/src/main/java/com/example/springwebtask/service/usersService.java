package com.example.springwebtask.service;

import com.example.springwebtask.Repository.userDao;

import com.example.springwebtask.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class usersService implements IUsersService{

    @Autowired
    private userDao userRepository;

    @Override
    public User findById(String id, String password) {

        return userRepository.findById(id,password);
    }



}
