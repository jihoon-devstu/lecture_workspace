package com.sinse.electroshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/electro")
    public String electroMain() {
        return "electro/index";
    }

    @GetMapping("/admin")
    public String adminMain() {
        return "admin/index";
    }

}
