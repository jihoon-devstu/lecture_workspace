package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaProductDAO implements ProductDAO {

    private final JpaProductRepository jpaProductRepository;

    @Override
    public Product findById(int productId) {
        return jpaProductRepository.findById(productId).get();
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return jpaProductRepository.save(product);
    }

    @Override
    public List<Product> findByStore_storeId(int storeId) {
        return jpaProductRepository.findByStore_storeId(storeId);
    }
}
