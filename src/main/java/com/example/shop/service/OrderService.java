package com.example.shop.service;


import org.springframework.stereotype.Service;

import com.example.shop.entity.Order;


@Service
public interface OrderService {

	Order insert(Order order);
}
