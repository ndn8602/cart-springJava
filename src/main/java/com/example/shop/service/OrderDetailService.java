package com.example.shop.service;

import org.springframework.stereotype.Service;

import com.example.shop.dto.CartDetailDto;

@Service
public interface OrderDetailService {

	void insert(CartDetailDto cartDetailDto);
}
