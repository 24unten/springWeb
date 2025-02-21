package com.example.springwebtask.controller;

import com.example.springwebtask.Form.AddForm;
import com.example.springwebtask.Form.LoginForm;
import com.example.springwebtask.service.IUsersService;
import com.example.springwebtask.service.Iproductsservice;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
public class controller {

    @Autowired
    IUsersService usersService;
    @Autowired
    HttpSession session;

    @Autowired
    Iproductsservice productsService;

    //login
    @GetMapping("/login")
    public String index(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult,Model model) {

        if(bindingResult.hasErrors()) {
            return "login";
        }
        if(usersService.findById(loginForm.getLoginId(), loginForm.getPassword())!=null) {
            System.out.println(usersService.findById(loginForm.getLoginId(), loginForm.getPassword()));
            session.setAttribute("user",usersService.findById(loginForm.getLoginId(), loginForm.getPassword()) );
            return "redirect:/menu";
        }
        model.addAttribute("errorMsg","IDまたはパスワードが不正です");
        return "login";
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(name="keyword",defaultValue="") String keyword, Model model) {
        if(keyword.isEmpty()) {
            model.addAttribute("productList",productsService.findAll());
        }else {
            model.addAttribute("productList",productsService.findByName(keyword));
        }

        return "menu";
    }

    @GetMapping("/insert")
    public  String insert(@ModelAttribute("addForm") AddForm addForm){
        return "insert";
    }

    @PostMapping("/insert")
    public String pinsert(@Validated @ModelAttribute("addForm") AddForm addForm, BindingResult bindingResult,Model model) {

        if(bindingResult.hasErrors()) {
            return "insert";
        }
        System.out.println(addForm);
        productsService.insert(addForm);
        return "redirect:/menu";
    }



}
