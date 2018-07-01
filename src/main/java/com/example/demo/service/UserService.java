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
    IUserDao iUserDao;

    @Autowired
    public UserService(IUserDao iUserDao) {
        this.iUserDao = iUserDao;
    }

    @Override
    public List<User> login(String username, String password) {
        return iUserDao.findAllByUsernameAndPassword(username, password);
    }

    public void register(String username, String password) {
        User user = new User(username, password);
        iUserDao.save(user);
    }

    @Override
    public void delete(String username, String password) {
        User user = login(username, password).get(0);
        iUserDao.delete(user);
    }

    @Override
    public void delete(Integer id) {
        User user = iUserDao.findAllById(id);
        iUserDao.delete(user);
    }

    @Override
    public void update(String username, String password, String newPassword) {
        User user = login(username, password).get(0);
        user.setPassword(newPassword);
        iUserDao.save(user);
    }
}
