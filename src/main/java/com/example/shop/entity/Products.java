package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;// 1
    @Column(name = "name")
    private String name;// beff
    @Column(name = "imgUrl")
    private String imgUrl;// beff
    @Column(name = "quantity")
    private Integer quantity;// 1000
    @Column(name = "price")
    private Double price; // 100
    @Column(name = "category")
    private String category; // meat
    @Column(name = "description")
    private String description; // meat

    @ManyToOne
    @JoinColumn(name = "typeId", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "aplications", "hibernateLazyInitializer" })
    private ProductTypes productType;

}
