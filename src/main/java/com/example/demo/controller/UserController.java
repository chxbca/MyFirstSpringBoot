package com.example.demo.controller;

import com.example.demo.WebSecurityConfig;
import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final
    IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/loginPost")
    public Map<String, Object> loginPost(User user, HttpSession session, HttpServletResponse response) throws IOException {
//        System.out.println("MainController.loginPost");
        String username = user.getUsername();
        String password = user.getPassword();
        int size = userService.login(username, password).size();
        Map<String, Object> map = new HashMap<>();
        if (size == 0) {
            map.put("success", false);
            map.put("message", "密码错误");
            return map;
        }

        // 设置session
        session.setAttribute(WebSecurityConfig.SESSION_KEY, username);

        map.put("success", true);
        map.put("message", "登录成功");
        String url = "/";
        response.sendRedirect(url);
        return map;
    }

    @PostMapping(path = "/user/register")
    public User register(@Valid User user, @RequestParam("repassword") String rePassword, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();
        String url = "/";
        response.sendRedirect(url);
        return userService.register(username, password);
    }

    @PostMapping(path = "/user/delete")
    public String delete(@Valid User user, HttpServletResponse response) throws IOException {
        Integer id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        if (id == null) {
            userService.delete(username, password);
        } else {
            userService.delete(id);
        }
        String url = "/";
        response.sendRedirect(url);
        return "删除成功";
    }

    @PostMapping(path = "/user/update")
    public User update(@Valid User user, @RequestParam("newpassword") String newPassword, HttpServletResponse response) throws IOException {
        String username = user.getUsername();
        String password = user.getPassword();
        String url = "/";
        response.sendRedirect(url);
        return userService.update(username, password, newPassword);
    }

    @GetMapping("/error")
    public ModelAndView systemError(@ModelAttribute("error") String error) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
