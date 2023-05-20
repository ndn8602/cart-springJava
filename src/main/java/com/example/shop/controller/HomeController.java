package com.example.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.shop.entity.ProductTypes;
import com.example.shop.entity.Products;
import com.example.shop.service.SessionService;
import com.example.shop.service.ProductService;
import com.example.shop.entity.Users;
import com.example.shop.service.*;


@Controller
public class HomeController {
    @Autowired
    userService usersService;
    @Autowired
    ProductService productsService;
    @Autowired
    ProductTypeService productTypeService;
    @Autowired
    SessionService session;

    @GetMapping("/")
    public String index(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        List<Products> products = new ArrayList<>();
        List<ProductTypes> productTypes = new ArrayList<>();
        try {
            Page<ProductTypes> pageProductsType = productTypeService.findAll(2, page);
            Page<Products> pageProducts = productsService.findAll(6, page);
            products = pageProducts.getContent();
            productTypes = pageProductsType.getContent();
            System.out.println("type " + productTypes.get(0).getDescription());
            model.addAttribute("totalPages", pageProducts.getTotalPages());
            model.addAttribute("currentPage", page);
        } catch (Exception ex) {
            products = productsService.findAll();
            productTypes = productTypeService.findAll();
            System.out.println("type " + productTypes.get(0).getDescription());
        }
        model.addAttribute("productTypes", productTypes);
        model.addAttribute("products", products);
        return "home";
    }

    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("userRequest", new Users());
        return "login";
    }

    @PostMapping("/loginpost")
    public String doPostLogin(@ModelAttribute Users userRequest,
            HttpSession session) {
        try {
            Users userResponse = usersService.doLogin(userRequest);
            if (!ObjectUtils.isEmpty(userResponse)) {
                session.setAttribute("user", userResponse);
                return "redirect:/";
            }
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/registerpost")
    public String doPostRegister(@ModelAttribute Users userRequest,
            HttpSession session) {
        System.out.println(userRequest);
        try {
            Users userResponse = usersService.save(userRequest);
            if (!ObjectUtils.isEmpty(userResponse)) {
                // session.setAttribute(SessionConstant.CURRENT_USER, userResponse);
                return "redirect:/login";
            } else {
                return "redirect:/register";
            }
        } catch (Exception ex) {
            return "redirect:/register";
        }
    }

    // Tìm kiếm sản phẩm theo tên
    @GetMapping("/search")
    public String search(Model model, @RequestParam("a") Optional<Integer> p,
            @RequestParam("keywords") Optional<String> kw) {
        String keywords = kw.orElse(session.get("keywords", ""));
        Products page = productsService.findByName("%" + keywords + "%");
        model.addAttribute("items", page);
        return "productDetails";
    }

    // Fillter
    @GetMapping("/filter")
    public String index(Model model, @RequestParam("a") Optional<Integer> p, @RequestParam("sort") Optional<String> cid,
            @RequestParam("name") Optional<String> name) {
        Pageable pageable = PageRequest.of(p.orElse(0), 10);
        Page<Products> listt = null;
        if (cid.isPresent()) {
            listt = productsService.findByProductTypeDescription(cid.get(), pageable);
        } else {
            listt = productsService.findAll(pageable);
        }
        model.addAttribute("products", listt);
        int totalPages = listt.getTotalPages();
        List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
        model.addAttribute("pageNumbers", pageNumbers);
        return "home";
    }

}
