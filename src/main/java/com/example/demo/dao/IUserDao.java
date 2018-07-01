package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserDao extends JpaRepository<User, Integer> {
    List<User> findAllByUsernameAndPassword(String userName, String password);

    User findAllById(Integer id);

    User getUserByUsernameAndPassword(String username, String password);

}
