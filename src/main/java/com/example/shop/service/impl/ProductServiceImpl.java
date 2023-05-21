package com.example.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.shop.entity.Products;
import com.example.shop.repository.ProductRepo;
import com.example.shop.service.ProductService;



@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Products> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Page<Products> findAll(int pageSize, int pageNumber) throws Exception {
        if (pageNumber >= 1) {
            return productRepo.findAllAvailable(PageRequest.of(pageNumber, pageSize));
        } else {
            throw new Exception("Page number must be greater than 0");
        }
    }

    @Override
    public List<Products> findByIdfindByProductTypeId(Integer cid) {
        return productRepo.findByIdfindByProductTypeId(cid);
    }

    @Override
    public Page<Products> findByProductTypeDescription(String integer, Pageable pageable) {
        return productRepo.findByProductTypeDescription(integer, pageable);
    }

    @Override
    public Page<Products> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return productRepo.findAllAvailable(pageable);
    }

    @Override
    public Page<Products> findByKeyWords(String string, Pageable pageable) {
        // TODO Auto-generated method stub
        return productRepo.findByKeywords(string, pageable);
    }

    @Override
    public Products findById(Long id) {
        Optional<Products> result = productRepo.findById(id); // ->select * from products where id=id
        return result.isPresent() ? result.get() : null;
    }

    @Override
    public Products findByName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public void updateQuantity(Integer newQuantity, Long id) {
    	productRepo.updateQuantity(newQuantity, id);
        
    }

}
