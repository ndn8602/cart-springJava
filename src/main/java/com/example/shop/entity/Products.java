package com.example.shop.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
