package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaProductDAO implements ProductDAO {

    private final ProductRepository productRepository;

    @Override
    public Product findById(int product_id) {
        return productRepository.findById(product_id).get();
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
