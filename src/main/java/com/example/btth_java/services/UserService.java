package com.example.btth_java.services;


import com.example.btth_java.entity.User;

public interface UserService {
    User findByUsername(String username);
    User findByEmail(String email);
    void save(User user);
}
