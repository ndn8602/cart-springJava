// package com.example.shop.controller;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;
// import java.util.stream.IntStream;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.example.shop.entity.Products;
// import com.example.shop.service.SessionService;
// import com.example.shop.service.productService;

// @Controller
// public class ProductController {
//     @Autowired
//     productService service;
//     @Autowired
//     SessionService session;

//     @GetMapping(value = "/index")
//     public String getMethodName(@RequestParam String param) {
//         return null;
//     }

//     // Tìm kiếm sản phẩm theo tên
//     // @GetMapping("/search")
//     // public String search(Model model, @RequestParam("a") Optional<Integer> p,
//     // @RequestParam("keywords") Optional<String> kw) {
//     // String keywords = kw.orElse(session.get("keywords", ""));
//     // session.set("keywords", keywords);
//     // Pageable pageable = PageRequest.of(p.orElse(0), 6);
//     // Page<Products> page = service.findByKeyWords("%" + keywords + "%", pageable);
//     // model.addAttribute("items", page);
//     // int totalPages = page.getTotalPages();
//     // List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//     // .boxed()
//     // .collect(Collectors.toList());
//     // model.addAttribute("pageNumbers", pageNumbers);

//     // return "user_html/product";
//     // }

//     @GetMapping("/index")
//     public String index(Model model, @RequestParam("a") Optional<Integer> p, @RequestParam("sort") Optional<String> cid,
//             @RequestParam("name") Optional<String> name) {
//         Pageable pageable = PageRequest.of(p.orElse(0), 6);
//         Page<Products> listt = null;
//         if (cid.isPresent()) {
//             listt = service.findByProductTypeDescription(cid.get(), pageable);
//             System.out.println(listt);
//         } else {
//             return "home";
//         }
//         model.addAttribute("items", listt);
//         int totalPages = listt.getTotalPages();
//         List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
//                 .boxed()
//                 .collect(Collectors.toList());
//         model.addAttribute("pageNumbers", pageNumbers);
//         return "home";
//     }
// }
