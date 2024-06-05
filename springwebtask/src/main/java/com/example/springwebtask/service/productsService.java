package com.example.springwebtask.service;

import com.example.springwebtask.Form.AddForm;
import com.example.springwebtask.Repository.PgProductsDao;
import com.example.springwebtask.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productsService implements Iproductsservice{

    @Autowired
    private PgProductsDao productsDao;

    @Override
    public List<Product> findAll() {
        return productsDao.findAll();
    }

    @Override
    public List<Product> findByName(String keyword) {
        return productsDao.findByName(keyword);
    }

    @Override
    public void insert(AddForm addForm) {
        productsDao.insert(addForm);
    }

    public void update(AddForm addForm,int id){
        productsDao.update(addForm,id);
    }

    @Override
    public Product findById(int id) {
        return productsDao.findById(id);
    }

    @Override
    public void delete(int id) { productsDao.delete(id);

    }
}
