package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaProductRepository extends JpaRepository<Product,Integer> {
    public List<Product> findByStore_storeId(int storeId);
}
