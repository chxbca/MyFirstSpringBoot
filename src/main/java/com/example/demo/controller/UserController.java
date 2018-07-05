package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final
    IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user/userpage")
    public String login(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        int size = userService.login(username, password).size();
        if (size == 0) {
            return "登陆失败";
        } else {
            return "登陆成功";
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/user/register")
    public void register(@RequestParam("un") String username,
                         @RequestParam("pwd") String password) {
        userService.register(username, password);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/delete")
    public void delete(@RequestParam("un") String username,
                       @RequestParam("pwd") String password,
                       @RequestParam("id") Integer id) {
        if (id == null) {
            userService.delete(username, password);
        } else {
            userService.delete(id);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "/user/update")
    public void update(@RequestParam("un") String username,
                       @RequestParam("pwd") String password,
                       @RequestParam("npwd") String newPassword) {
        userService.update(username, password, newPassword);
    }
}
