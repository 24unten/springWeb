package com.example.springwebtask.service;

import com.example.springwebtask.Repository.IcategoryDao;
import com.example.springwebtask.entity.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class categoriesService implements IcategoriesServis{
    @Autowired
    public IcategoryDao categoryDao;

    @Override
    public List<category> findAll() {
        return categoryDao.findAll();
    }


}
