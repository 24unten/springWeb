package com.example.springwebtask.Repository;

import com.example.springwebtask.Form.AddForm;
import com.example.springwebtask.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class productsDao implements PgProductsDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT products.id,product_id,categories.name AS category_name ,products.name,price," +
                        "image_path,description,products.created_at,products.updated_at FROM products " +
                        "JOIN categories ON products.category_id = categories.id ORDER BY product_id",
                new DataClassRowMapper<>(Product.class));
    }

    @Override
    public List<Product> findByName(String keyword) {
        var param = new MapSqlParameterSource();
        param.addValue("keyword", "%"+keyword+"%");
        return jdbcTemplate.query("SELECT products.id,product_id,categories.name AS category_name ,products.name,price,image_path,description,products.created_at,products.updated_at FROM products JOIN categories ON products.category_id = categories.id where products.name like :keyword ORDER BY product_id", param, new DataClassRowMapper<>(Product.class));
    }

    @Override
    public void insert(AddForm addForm){
        var param = new MapSqlParameterSource();
        param.addValue("product_id", addForm.getProduct_id());
        param.addValue("name", addForm.getName());
        param.addValue("price", Integer.parseInt(addForm.getPrice()));
        param.addValue("category_id", Integer.parseInt(addForm.getCategory_id()));
        param.addValue("description", addForm.getDescription());
        param.addValue("img_path", addForm.getImg_path());
        jdbcTemplate.update("INSERT INTO products (product_id,name,price,category_id,description,image_path) VALUES (:product_id,:name,:price,:category_id,:description,:img_path)", param);
    }

    @Override
    public void update(AddForm addForm){
        var param = new MapSqlParameterSource();
        param.addValue("product_id", addForm.getProduct_id());
        param.addValue("name", addForm.getName());
        param.addValue("price", Integer.parseInt(addForm.getPrice()));
        param.addValue("category_id", Integer.parseInt(addForm.getCategory_id()));
        param.addValue("description", addForm.getDescription());
        param.addValue("img_path", addForm.getImg_path());
        jdbcTemplate.update("update products SET name = :name, price = :price category_id = :category_id" +
                "description = :description img_path = :img_path WHERE product_id = :product_id", param);
    }

    @Override
    public Product findById(int id){
        System.out.println(id);
        var param = new MapSqlParameterSource();
        param.addValue("id", id);
        var detail =  jdbcTemplate.query("SELECT products.id,product_id,categories.name AS category_name ,products.name,price,image_path,description,products.created_at,products.updated_at FROM products JOIN categories ON products.category_id = categories.id where products.id = :id", param, new DataClassRowMapper<>(Product.class));
        return detail.get(0);
    }
}
