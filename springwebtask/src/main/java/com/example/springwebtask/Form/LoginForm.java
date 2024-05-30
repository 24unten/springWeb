package com.example.springwebtask.Form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {

    @NotEmpty(message = "IDは必須入力です")
    private String loginId;
    @NotEmpty(message = "パスワードは必須入力です")
    private String password;

}
