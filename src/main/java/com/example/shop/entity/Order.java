package com.example.shop.entity;

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
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "    ")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "createDate")
    private Timestamp createDate;

    @ManyToOne
	@JoinColumn(name="userId", referencedColumnName ="id")
	@JsonIgnoreProperties(value= {"aplications","hibernateLazyInitializer"})
	private Users user;
}
// go
// create table order (
// productID int,
// nameProduct varchar(255),
// quantity INT,
// saled INT,
// City varchar(255)
// )