package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", length = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "username", length = 11)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @Basic
    @Column(name = "password", length = 11)
    @NotEmpty(message = "密码不能为空")
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("{id:").append(id).append("userneme:").append(username).append("password:").append(password).append("}").toString();
    }
}
