package com.example.shop.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.dto.CartDto;
import com.example.shop.entity.Users;
import com.example.shop.service.CartService;
import com.example.shop.service.SessionUtil;


@RestController
@RequestMapping("api/cart")
public class CartApi {

	@Autowired
	private CartService cartService;
	
	// /api/cart/update?productId=...&quantity=...&isReplace=...
	
	@GetMapping("/update")
	public ResponseEntity<?>doGetUpdate(@RequestParam("productId")Long productId,
			@RequestParam("quantity") Integer quantity,
			@RequestParam("isReplace") Boolean isReplace,
			HttpSession session){
		
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, productId, quantity, isReplace);		
		return ResponseEntity.ok(currentCart);	
	}

	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefreshData(HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		return ResponseEntity.ok(currentCart);
	}

	// api/cart/checkout?address=...&phone=...
	

	@GetMapping("/checkout")
	public ResponseEntity<?> doGetCheckout(@RequestParam("address") String address, @RequestParam("phone") String phone,
			HttpSession session) {
		Users currentUser = SessionUtil.getCurrentUser(session);
		if (ObjectUtils.isEmpty(currentUser)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		try {
			cartService.insert(currentUser,currentCart,phone, address);
			session.setAttribute("currentCart", new CartDto());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}
