package com.example.shop.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Order;
import com.example.shop.repository.OrdersRepo;
import com.example.shop.service.OrderService;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
 
@Service
public class OrderServiceImbl implements OrderService{

	@Autowired
	private OrdersRepo repo;
		
	@Transactional(value = TxType.REQUIRED)
	@Override
	public Order insert(Order order) {
		// TODO Auto-generated method stub
		return repo.saveAndFlush(order);
	}

	
}
