package com.example.demo.service;

import com.example.demo.dao.IUserDao;
import com.example.demo.model.User;
import com.example.demo.service.implement.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    private final
    IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> login(String username, String password) {
        return userDao.findAllByUsernameAndPassword(username, password);
    }

    @Override
    public List<User> getAllUserByUsername(String username) {
        return userDao.findAllByUsername(username);
    }

    @Override
    public List<User> getAllUser() {
        return userDao.findAll();
    }

    public User register(String username, String password) {
        User user = new User(username, password);
        return userDao.save(user);
    }

    @Override
    public void delete(String username, String password) {
        User user = login(username, password).get(0);
        userDao.delete(user);
    }

    @Override
    public void delete(Integer id) {
        User user = userDao.findAllById(id);
        userDao.delete(user);
    }

    @Override
    public User update(String username, String password, String newPassword) {
        User user = login(username, password).get(0);
        user.setPassword(newPassword);
        return userDao.save(user);
    }
}
