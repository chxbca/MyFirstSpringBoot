package com.example.demo.controller;

import com.example.demo.WebSecurityConfig;
import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(path = "/login")
    public Object login(ModelAndView modelAndView, HttpSession session) {
        if (session.getAttribute(WebSecurityConfig.SESSION_KEY) != null) {
            return "redirect:/";
        } else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(User user, HttpSession session, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();
        int size = userService.login(username, password).size();
        String url = "/login";
        if (size == 0) {
            response.sendRedirect(url);
        } else {
            session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
            url = "/";
            response.sendRedirect(url);
        }
        return userService.login(username, password).get(0);
    }

    @GetMapping(path = "/alluser")
    public Object allUser(Model model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("user", list);
        return model;
    }

    @GetMapping(path = "/register")
    public Object register(ModelAndView modelAndView) {
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public Object register(@Valid User user, @RequestParam("repassword") String rePassword, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();
        int size = userService.getAllUserByUsername(username).size();
        String url;

        if (!password.equals(rePassword) || size != 0) {
            url = "/register";
            response.sendRedirect(url);
            return null;
        }
        url = "/";
        response.sendRedirect(url);
        return userService.register(username, password);
    }

    @GetMapping(path = "/delete")
    public Object delete(ModelAndView modelAndView) {
        modelAndView.setViewName("delete");
        return modelAndView;
    }

    @PostMapping(path = "/delete")
    @ResponseBody
    public Object delete(@Valid User user, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();
        userService.delete(username, password);
        String url = "/";
        response.sendRedirect(url);
        System.out.println("删除成功");
        return "删除成功";
    }


    @GetMapping(path = "/update")
    public Object update(ModelAndView modelAndView) {
        modelAndView.setViewName("update");
        return modelAndView;
    }

    @PostMapping(path = "/update")
    @ResponseBody
    public Object update(@Valid User user, @RequestParam("newpassword") String newPassword, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();
        String url = "/";
        response.sendRedirect(url);
        return userService.update(username, password, newPassword);
    }

    @GetMapping(path = "/")
    public Object index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String username, Model model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("user", list);
        model.addAttribute("name", username);
        return "alluser";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }

    @GetMapping("/error")
    public ModelAndView systemError(@ModelAttribute("error") String error) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
