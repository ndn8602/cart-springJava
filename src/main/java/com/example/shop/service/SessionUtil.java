package com.example.shop.service;

import com.example.shop.dto.CartDto;
import com.example.shop.entity.Users;

import jakarta.servlet.http.HttpSession;

public class SessionUtil {
	
	private  SessionUtil() {}

	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute(SessionConstant.CURRENT_CART);
	}
	
	public static Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute(SessionConstant.CURRENT_USER);
	}
}
