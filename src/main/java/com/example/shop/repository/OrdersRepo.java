package com.example.shop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shop.entity.Order;

@Repository
//extend entity
public interface OrdersRepo extends JpaRepository<Order, Long>{

	
}
 