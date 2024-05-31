package com.example.springwebtask.Repository;

import com.example.springwebtask.Form.AddForm;
import com.example.springwebtask.entity.Product;

import java.util.List;

public interface PgProductsDao {

    List<Product> findAll();

    List<Product> findByName(String  keyword);

    void insert(AddForm addForm);

    void update(AddForm addForm);

    Product findById(int id);
}
