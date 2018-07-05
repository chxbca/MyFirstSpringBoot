package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    private final
    IUserService userService;

    @Autowired
    public IndexController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/")
    public Object index(Model model) {
        return "login";
    }

    @RequestMapping(path = "/login")
    public Object login() {
        return null;
    }

    @RequestMapping(path = "/register")
    public Object register() {
        return null;
    }

    @RequestMapping(path = "/delete")
    public Object delete() {
        return null;
    }


    @RequestMapping(path = "/update")
    public Object update() {
        return null;
    }

    @RequestMapping(path = "/user/allUser")
    public Object allUser(Model model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("user", list);
        return null;
    }
}