package com.example.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dto.CartDetailDto;
import com.example.shop.repository.OrderDetailsRepo;
import com.example.shop.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	OrderDetailsRepo repo;

	@Override
	public void insert(CartDetailDto cartDetailDto) {
		repo.insert(cartDetailDto);

	}

}
