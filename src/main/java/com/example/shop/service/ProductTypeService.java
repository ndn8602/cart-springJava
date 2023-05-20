package com.example.shop.service;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.shop.entity.ProductTypes;

@Service
public interface ProductTypeService {
    List<ProductTypes> findAll();

    Page<ProductTypes> findAll(int pageSize, int pageNumber) throws Exception;
}
