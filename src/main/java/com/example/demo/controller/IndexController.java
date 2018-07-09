package com.example.demo.controller;

import com.example.demo.WebSecurityConfig;
import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
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
    public Object index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username, Model model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("user", list);
        model.addAttribute("name", username);
        return "alluser";
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

    @RequestMapping(path = "/alluser")
    public Object allUser(Model model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("user", list);
        return null;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }
}