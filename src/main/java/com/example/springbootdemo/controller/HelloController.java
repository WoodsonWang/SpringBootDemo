package com.example.springbootdemo.controller;

import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class HelloController {

    @Autowired
    UserDao userDao;

    @RequestMapping("/login")
    public String loginView(){
        return "login";
    }


    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public String login(String email, String password, ModelMap map){
        System.out.println(email+"====="+password);
        User user = userDao.findByName(email,password);
        System.out.println(user.toString());
        if (user == null){
            return "login";
        }else{
            List<User> users = userDao.getUsers();
            map.addAttribute("users",users);
            return "success";
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(String username,String email,String password,ModelMap map){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        int a = 1;

        user.setPassword(password);
        int result = userDao.addUser(user);
        if (result == 1){
            List<User> users = userDao.getUsers();
            map.addAttribute("users",users);
            return "success";
        }else{
            return "";
        }


    }



}
