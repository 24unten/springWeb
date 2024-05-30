package com.example.springwebtask.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AddForm {

    @NotEmpty(message = "IDは必須入力です")
    private String product_id;

    @NotEmpty(message = "商品名は必須入力です")
    private String name;

    @NotEmpty(message = "単価は必須入力です")
    private String price;

    @NotEmpty(message = "カテゴリは必須入力です")
    private String category_id;

    private String description;

    private String img_path;
}
