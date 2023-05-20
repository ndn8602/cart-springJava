package com.example.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.shop.entity.ProductTypes;
import com.example.shop.repository.ProductTypeRepo;
import com.example.shop.service.ProductTypeService;

@Service
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepo repo;

    @Override
    public List<ProductTypes> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<ProductTypes> findAll(int pageSize, int pageNumber) throws Exception {
        if (pageNumber >= 1) {
            return repo.findAllAvailable(PageRequest.of(pageNumber, pageSize));
        } else {
            throw new Exception("Page number must be greater than 0");
        }
    }

}
