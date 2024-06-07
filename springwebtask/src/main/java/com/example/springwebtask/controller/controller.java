package com.example.springwebtask.controller;

import com.example.springwebtask.Form.AddForm;
import com.example.springwebtask.Form.LoginForm;
import com.example.springwebtask.service.IUsersService;
import com.example.springwebtask.service.IcategoriesServis;
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

    @Autowired
    IcategoriesServis categoriesService;

    private String[] successMsgList = {"","追加が成功しました！","更新が成功しました!!!","!削除が成功しました!"};
    private int successIndex = 0;

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
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }

        if(keyword.isEmpty()) {
            model.addAttribute("productList",productsService.findAll());
        }else {
            model.addAttribute("productList",productsService.findByName(keyword));
        }
        model.addAttribute("successMsg",successMsgList[successIndex]);
        successIndex = 0;
        return "menu";
    }

    @GetMapping("/insert")
    public  String insert(@ModelAttribute("addForm") AddForm addForm,Model model) {
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }
        model.addAttribute("categoryList",categoriesService.findAll());
        return "insert";
    }

    @PostMapping("/insert")
    public String pinsert(@Validated @ModelAttribute("addForm") AddForm addForm, BindingResult bindingResult,Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("categoryList",categoriesService.findAll());
            return "insert";
        }
        try{

            productsService.insert(addForm);
            successIndex = 1;
            return "redirect:/menu";
        }catch (RuntimeException e){
            System.out.println("catch");
            model.addAttribute("errorMsg","商品コードが重複しています");
            return "insert";
        }



    }




    @GetMapping("/updateInput/{id}")
    public String update(@PathVariable("id") int id,@ModelAttribute("addForm") AddForm addForm,Model model){
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }
        System.out.println("a");
        model.addAttribute("product",productsService.findById(id));
        model.addAttribute("categoryList",categoriesService.findAll());
        System.out.println("a");
        var user = productsService.findById(id);
        addForm.setCategory_id(user.category_name());
        addForm.setDescription(user.description());
        addForm.setName(user.name());
        addForm.setPrice(String.valueOf(user.price()));
        addForm.setImg_path(user.image_path());
        addForm.setProduct_id(user.product_id());
        return "updateInput";
    }

    @PostMapping("/updateInput/{id}")
    public String updateInput(@PathVariable("id") int id,@Validated @ModelAttribute("addForm") AddForm addForm, BindingResult bindingResult,Model model) {

        if(bindingResult.hasErrors()) {
            return "updateInput";
        }
        try{
            productsService.update(addForm,id);
            successIndex = 2;
            return "redirect:/menu";
        }catch (RuntimeException e){
            model.addAttribute("errorMsg","商品コードが重複しています");
            return "updateInput";
        }

    }

    @GetMapping("/category")
    public String category(Model model){
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }
        model.addAttribute("categoryList",categoriesService.findAll());
        System.out.println(categoriesService.findAll());
        return "category";
    }

    @GetMapping("/logout")
    private String logout(){
        session.removeAttribute("user");
        return "logout";
    }

    @GetMapping("/delete/{id}")
    public String delete(@Validated @PathVariable("id") int id,Model model){
        productsService.delete(id);
        successIndex = 3;
        return "delete";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") int id ,Model model){
        if(session.getAttribute("user")==null){
            return "redirect:/login";
        }
        System.out.println(productsService.findById(id));
        model.addAttribute("product",productsService.findById(id));
        return  "detail";
    }


}
