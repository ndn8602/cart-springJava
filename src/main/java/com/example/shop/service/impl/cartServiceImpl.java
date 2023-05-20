package com.example.shop.service.impl;

import java.util.HashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shop.dto.CartDetailDto;
import com.example.shop.dto.CartDto;
import com.example.shop.entity.Order;
import com.example.shop.entity.Products;
import com.example.shop.entity.Users;
import com.example.shop.service.OrderService;
import com.example.shop.service.CartService;
import com.example.shop.service.OrderDetailService;
import com.example.shop.service.productService;

import jakarta.transaction.Transactional;
@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private productService productService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Override
	public CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace) {
		Products product = productService.findById(productId);
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		// 1 - thêm mới
		// 2- update
		// 2,1 cộng dồn
		// 2.2 thay thế hoàn toàn
		// 3 delete (update vs quantity =0)

		if (!details.containsKey(productId)) {
			// thêm mới
			CartDetailDto cartDetailDto = createNewCartDetail(product, quantity);
			details.put(productId, cartDetailDto);
		} else if (quantity > 0) {
			// update
			if (isReplace) {
				details.get(productId).setQuantity(quantity);
			} else {
				Integer curentQuantity = details.get(productId).getQuantity();
				Integer newQuantity = curentQuantity + quantity;
				details.get(productId).setQuantity(newQuantity);
			}
		} else {
			// delete
			details.remove(productId);
		}
		cart.setTotalQuantity(getTotalQuantity(cart));
		cart.setTotalPrice(getTotalPrice(cart));

		// update totalprice

		return cart;
	}

	@Override
	public Integer getTotalQuantity(CartDto cart) {
		Integer totalQuantity = 0;
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetail : details.values()) {
			totalQuantity += cartDetail.getQuantity();
		}
		return totalQuantity;
	}

	@Override
	public Double getTotalPrice(CartDto cart) {
		Double totalPrice = 0D;
		HashMap<Long, CartDetailDto> details = cart.getDetails();
		for (CartDetailDto cartDetail : details.values()) {
			totalPrice += (cartDetail.getPrice() * cartDetail.getQuantity());
		}
		return totalPrice;
	}

	private CartDetailDto createNewCartDetail(Products product, Integer quantity) {
		CartDetailDto cartDetailDto = new CartDetailDto();
		cartDetailDto.setProductId(product.getId());
		cartDetailDto.setQuantity(quantity);
		cartDetailDto.setPrice(product.getPrice());
		cartDetailDto.setImgUrl(product.getImgUrl());
		cartDetailDto.setName(product.getName());
		return cartDetailDto;
	}

	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	@Override
	// thao tac toi 3 bang: order,order_details,product
	public void insert(CartDto cartDto, Users user, String address, String phone) throws Exception {
		if (StringUtils.isAnyEmpty(address, phone)) {
			throw new Exception("Address or phone must be not null or empty or whitespace ");
		}

		// insert vao bang Orders
		Order order = new Order();
		order.setUser(user);
		order.setAddress(address);
		order.setPhone(phone);

		Order orderResponse = orderService.insert(order);

		if (ObjectUtils.isEmpty(orderResponse)) {
			throw new Exception("Insert into order table failed");
		}

		// inset vao bang order_details
		for (CartDetailDto cartDetailDto : cartDto.getDetails().values()) {

			Products product = productService.findById(cartDetailDto.getProductId());

			if (checkQuantity(product, cartDetailDto)) {
				cartDetailDto.setOrderId(orderResponse.getId());
				orderDetailService.insert(cartDetailDto);
				// update new quantity cho bang product
				Integer newQuantity = product.getQuantity() - cartDetailDto.getQuantity();
				productService.updateQuantity(newQuantity, product.getId());
			}

		}
	}

	private boolean checkQuantity(Products product, CartDetailDto cartDetail) {
		return product.getQuantity() >= cartDetail.getQuantity();
	}
}
