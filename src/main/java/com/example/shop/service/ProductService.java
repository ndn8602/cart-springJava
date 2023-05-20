package com.example.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Products;

@Service
public interface ProductService {

    List<Products> findAll();

    Page<Products> findAll(int pageSize, int pageNumber) throws Exception;
    Page<Products> findAll(Pageable pageable);
    Products findById(Long id);

    List<Products> findByIdfindByProductTypeId(Integer cid);

    Page<Products> findByKeyWords(String string, Pageable pageable);

    Products findByName(String name);

    void updateQuantity(Integer newQuantity, Long id);

    Page<Products> findByProductTypeDescription(String integer, Pageable pageable);

}
