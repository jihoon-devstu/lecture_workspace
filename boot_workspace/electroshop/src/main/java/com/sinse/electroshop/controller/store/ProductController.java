package com.sinse.electroshop.controller.store;

import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product/list")
    @ResponseBody
    public List<Product> getProductList(){
        List productList = productService.findAll();
        return productList;
    }

    @GetMapping("/product/detail")
    public String getDetail(Integer productId, Model model) {

        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "electro/product";

    }

}
