package com.sinse.electroshop.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //Mysql 의 auto_increment 매핑
    @Column(name="product_id")
    private int productId;

    private String productName;
    private String brand;
    private int price;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable=false)
    private Store store;

}
