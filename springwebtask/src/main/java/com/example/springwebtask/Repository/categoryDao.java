package com.example.springwebtask.Repository;

import com.example.springwebtask.entity.category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class categoryDao implements IcategoryDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<category> findAll() {
        return jdbcTemplate.query("SELECT categories.id,categories.name,categories.created_at,categories.updated_at FROM categories",
                new DataClassRowMapper<>(category.class));
    }


}
