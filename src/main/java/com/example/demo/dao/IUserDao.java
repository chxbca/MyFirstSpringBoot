package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public interface IUserDao extends JpaRepository<User, Integer> {
    List<User> findAllByUsernameAndPassword(@NotEmpty(message = "用户名不能为空") String username, @NotEmpty(message = "密码不能为空") String password);

    List<User> findAllByUsername(@NotEmpty(message = "用户名不能为空") String username);

    User getUserByUsernameAndPassword(@NotEmpty(message = "用户名不能为空") String username, @NotEmpty(message = "密码不能为空") String password);

    User findAllById(Integer id);

}
