package com.example.shop.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDto {


    private Long orderId;
    private Long productId;
    private String name;
    private Double price;
    private Integer quantity;
    private String imgUrl;
}
