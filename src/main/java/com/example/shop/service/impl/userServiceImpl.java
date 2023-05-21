package com.example.shop.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Users;
import com.example.shop.repository.UserRepo;
import com.example.shop.service.userService;


@Service
public class userServiceImpl implements userService {
    @Autowired
    private UserRepo repo;

    @Override
    public Users doLogin(Users userRequest) {
        // check username
        Users userResponse = repo.findByUsername(userRequest.getUsername());

        if (userResponse.getUsername().equals(userRequest.getUsername())
                && userResponse.getPassword().equals(userRequest.getPassword())) {
            return userResponse;
        }

        return null;
    }

    @Override
    @Transactional(rollbackOn = { Exception.class, Throwable.class })
    public Users save(Users user) {
        return repo.save(user);
    }

    @Override
    public List<Users> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

}
