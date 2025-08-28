package com.sinse.electroshop.model.product;

import com.sinse.electroshop.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;
    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public Product findById(int productId) {
        return productDAO.findById(productId);
    }

    @Override
    public Product save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public List getListByStoreId(int storeId) {
        return productDAO.findByStore_storeId(storeId);
    }
}
