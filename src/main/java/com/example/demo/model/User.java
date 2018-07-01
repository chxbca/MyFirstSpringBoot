package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private Integer id;
    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @Column(name = "id", length = 8)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "username", length = 11)
    public String getUsername() {
        return username;
    }

    @Basic
    @Column(name = "password", length = 32)
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
}
