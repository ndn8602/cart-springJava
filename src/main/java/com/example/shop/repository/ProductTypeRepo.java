package com.example.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.shop.entity.ProductTypes;

public interface ProductTypeRepo extends JpaRepository<ProductTypes, Integer> {
    @Query(value = "select * from product_types ", nativeQuery = true)
    Page<ProductTypes> findAllAvailable(Pageable pageable);
}
