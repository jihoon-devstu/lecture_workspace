package com.sinse.electroshop.controller.store;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/shop/main")
    public String index() {
        return "electro/index";
    }

}