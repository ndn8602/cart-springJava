package com.example.shop.service;

import com.example.shop.dto.CartDto;
import com.example.shop.entity.Users;

public interface cartService {
    CartDto updateCart(CartDto cart, Long productId, Integer quantity);

}
