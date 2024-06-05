package com.example.springwebtask.Repository;

import com.example.springwebtask.entity.category;

import java.util.List;

public interface IcategoryDao {

    List<category> findAll();
}
