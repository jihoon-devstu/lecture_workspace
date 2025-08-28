package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int productId);
    public Product save(Product product);
    public List getListByStoreId(int storeId);

}
