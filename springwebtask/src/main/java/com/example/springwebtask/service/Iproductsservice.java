package com.example.springwebtask.service;

import com.example.springwebtask.Form.AddForm;
import com.example.springwebtask.entity.Product;

import java.util.List;

public interface Iproductsservice {
    List<Product> findAll();

    List<Product> findByName(String keyword);

    void insert(AddForm addForm);

    void update(AddForm addForm,int id);

    Product findById(int id);
}
