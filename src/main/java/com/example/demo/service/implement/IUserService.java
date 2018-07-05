package com.example.demo.service.implement;

import com.example.demo.model.User;

import java.util.List;

public interface IUserService {
    List<User> login(String username, String password);

    List<User> getAllUser();

    User register(String username, String password);

    void delete(String username, String password);

    void delete(Integer id);

    User update(String username, String password, String newPassword);
}
