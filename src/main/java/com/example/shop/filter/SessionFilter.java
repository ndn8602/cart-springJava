package com.example.shop.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.example.shop.dto.CartDto;



@Component
public class SessionFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest= (HttpServletRequest) request;
		HttpSession session=httpServletRequest.getSession();
	    //goi validateCart
		validateCart(session);
		chain.doFilter(httpServletRequest, response);
	}
	
	private void validateCart(HttpSession session) {
		//Kiểm tra giỏ hàng có hay chưa .Chưa có cấp mới 
		if(ObjectUtils.isEmpty(session.getAttribute("currentCart"))) {
			session.setAttribute("currentCart", new CartDto());
		}
	}

}
