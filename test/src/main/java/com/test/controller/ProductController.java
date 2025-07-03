package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getAll(Model model) {
        model.addAttribute("products", productService.getAll());
        return "productList";
    }
    
    @GetMapping("/test")
    public String test() {
        return "redirect:/products";
    }
}
