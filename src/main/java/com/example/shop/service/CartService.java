package com.example.shop.service;

import org.springframework.stereotype.Service;

import com.example.shop.entity.Users;
import com.example.shop.dto.CartDto;

@Service
public interface CartService {
    CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);

    Integer getTotalQuantity(CartDto cart);

    Double getTotalPrice(CartDto cart);

    void insert(Users user,CartDto cartDto,  String address, String phone) throws Exception;
}
