package com.example.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.entity.Users;
@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
    
}
