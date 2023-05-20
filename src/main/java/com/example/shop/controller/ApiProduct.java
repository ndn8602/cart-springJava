package com.example.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.shop.entity.OrderDetail;
import com.example.shop.entity.Products;
import com.example.shop.service.productService;

@CrossOrigin("*")
@RestController
@RequestMapping("/product/list")
public class ApiProduct {
	@Autowired
	productService service;

	@GetMapping
	public List<Products> index() {
		return service.findAll();
	}

	@PostMapping("/buy")
	public String PostProduct(@RequestParam String cart) {
		return cart;
	}
}
