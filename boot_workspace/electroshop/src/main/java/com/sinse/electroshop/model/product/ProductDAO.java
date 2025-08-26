package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;

import java.util.List;

public interface ProductDAO {
    public Product findById(int product_id);
    public List<Product> findAll();
}
