package com.example.shop.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop.entity.Users;

@Service
public interface userService {

    Users doLogin(Users userReques);

    Users save(Users user) throws SQLException;

    List<Users> findAll();
}