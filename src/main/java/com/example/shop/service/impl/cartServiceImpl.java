// package com.example.shop.service.impl;

// import java.util.HashMap;

// import com.example.shop.dto.CartDetailDto;
// import com.example.shop.dto.CartDto;
// import com.example.shop.entity.Products;
// import com.example.shop.entity.Users;
// import com.example.shop.service.cartService;
// import com.example.shop.service.productService;

// public class cartServiceImpl implements cartService {

// 	@Override
// 	public CartDto updateCart(CartDto cart, Long productId, Integer quantity) {
// 		Products product = productService.findById(productId);
// 		HashMap<Long, CartDetailDto> details = cart.getDetails();
// 		// 1 - thêm mới
		

// 		if (!details.containsKey(productId)) {
// 			// thêm mới
// 			CartDetailDto cartDetailDto = createNewCartDetail(product, quantity);
// 			details.put(productId, cartDetailDto);
// 		}

// 		// update totalprice

// 		return cart;
// 	}

// 	private CartDetailDto createNewCartDetail(Products product, Integer quantity) {
// 		CartDetailDto cartDetailDto = new CartDetailDto();
// 		cartDetailDto.setProductId(product.getId());
// 		cartDetailDto.setQuantity(quantity);
// 		cartDetailDto.setPrice(product.getPrice());
// 		cartDetailDto.setImgUrl(product.getImgUrl());
// 		cartDetailDto.setName(product.getName());
// 		return cartDetailDto;
// 	}

// }
