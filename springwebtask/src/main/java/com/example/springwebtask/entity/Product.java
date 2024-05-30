package com.example.springwebtask.entity;

public record Product(int id,String product_id,String category_name,String name,int price,String image_path,String description,String created_at,String updated_at) {
}
