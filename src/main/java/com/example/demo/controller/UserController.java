package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final
    IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/login")
    public User login(@RequestParam("un") String username,
                      @RequestParam("pwd") String password) {
        return userService.login(username, password).get(0);
    }

    @GetMapping(path = "/register")
    public void register(@RequestParam("un") String username,
                         @RequestParam("pwd") String password) {
        userService.register(username, password);
    }

    @GetMapping(path = "/delete")
    public void delete(@RequestParam("un") String username,
                       @RequestParam("pwd") String password,
                       @RequestParam("id") Integer id) {
        if (id == null) {
            userService.delete(username, password);
        } else {
            userService.delete(id);
        }
    }

    @GetMapping(path = "/update")
    public void update(@RequestParam("un") String username,
                       @RequestParam("pwd") String password,
                       @RequestParam("npwd") String newPassword){
        userService.update(username, password, newPassword);
    }
}
