package com.test.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.Product;
import com.test.service.OrdersService;
import com.test.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersService orderService;

    @GetMapping("/form")
    public String showForm(Model model) {
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        return "orderForm";
    }

    @PostMapping("/submit")
    public String submitOrder(@RequestParam String customerName,
                               @RequestParam Map<String, String> paramMap,
                               Model model) {
        String message = orderService.createOrder(customerName, paramMap);
        model.addAttribute("message", message);
        model.addAttribute("products", productService.getAll());
        return "orderForm";
    }
}

