package com.sinse.electroshop.controller.admin;

import com.sinse.electroshop.domain.Product;
import com.sinse.electroshop.domain.Store;
import com.sinse.electroshop.model.product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminProductController {

    private final ProductService productService;

    @GetMapping("/product/registform")
    public String registForm() {
        return "admin/product/regist";
    }

    @PostMapping("/product/regist")
    @ResponseBody
    public String regist(Product product) {
            productService.save(product);
            return  "상품 등록 성공";
        }
    @GetMapping("/product/listbystore")
    public String getListByStore(Model model , @RequestParam(name="storeId", required = false)Integer storeId, HttpSession session) {
        if(storeId==null){
            Store store = (Store)session.getAttribute("store");
            storeId=store.getStoreId();
        }
        List productList = productService.getListByStoreId(storeId);
        model.addAttribute("productList", productList);
        return "admin/product/list";
    }


    @GetMapping("/product/detail")
    public String getDetail(@RequestParam(name = "product_id" , required = false) int productId, Model model) {
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }
}
