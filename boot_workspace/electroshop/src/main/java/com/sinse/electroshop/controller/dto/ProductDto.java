package com.sinse.electroshop.controller.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String product_id;
    private String product_name;
    private int price;
    private String brand;

}
