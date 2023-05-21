package com.example.shop.service;

import javax.servlet.http.HttpSession;

import com.example.shop.dto.CartDto;
import com.example.shop.entity.Users;



public class SessionUtil {
	
	private  SessionUtil() {}

	public static CartDto getCurrentCart(HttpSession session) {
		return (CartDto) session.getAttribute("currentCart");
	}
	
	public static Users getCurrentUser(HttpSession session) {
		return (Users) session.getAttribute("user");
	}
}
