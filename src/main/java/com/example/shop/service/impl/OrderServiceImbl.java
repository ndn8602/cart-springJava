package com.example.shop.service.impl;



import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Order;
import com.example.shop.repository.OrdersRepo;
import com.example.shop.service.OrderService;


 
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
