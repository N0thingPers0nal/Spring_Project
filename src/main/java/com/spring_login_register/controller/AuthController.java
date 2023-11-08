package com.spring_login_register.controller;

import com.spring_login_register.dao.UserDao;
import com.spring_login_register.db.DatabaseConnects;
import com.spring_login_register.model.User;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;

@Controller
public class AuthController {
    private UserDao userDao;
    public AuthController() {
        userDao=new UserDao();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);

        binder.registerCustomEditor(String.class, editor);
    }
    @RequestMapping("/index")
    public String showIndexPage(Model model){
        User user=new User();
        model.addAttribute("user", user);
        return "index";
    }
    @RequestMapping("/login")
    public String loginPage(HttpServletRequest httpServletRequest){
//        User user=new User();
//        model.addAttribute("user", user);
        String name= httpServletRequest.getParameter("email");
        String password=httpServletRequest.getParameter("password");

        User user=userDao.isLoggedIn(name,password);
        if(user!=null){
            List<User> userList=userDao.getAllUsers();
            httpServletRequest.setAttribute("users", userList);
            return "home";
        }
        return "index";
    }
    @RequestMapping("/register")
    public String registerPage(){
        return "register";
    }
    @RequestMapping("/registerForm")
    public String registerPage(HttpServletRequest httpServletRequest){
        System.out.println("aaaa");
        String name=httpServletRequest.getParameter("name");
        String email=httpServletRequest.getParameter("email");
        String password=httpServletRequest.getParameter("password");
        String c_password=httpServletRequest.getParameter("c_password");
        if(Objects.equals(c_password, password)){
            if(!userDao.register(name,email,password,c_password)){

                return "index";
            }
        }
        return "register";
    }
    @RequestMapping("/home")
    public String homePage(
        @Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
            if(bindingResult.hasErrors()) {
                return "index";
            }

            return "home";
    }
}
