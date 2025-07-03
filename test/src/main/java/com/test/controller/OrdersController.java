package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.dto.OrderRequestDTO;
import com.test.dto.OrderResultDTO;
import com.test.entity.Product;
import com.test.service.OrdersService;
import com.test.service.ProductService;

@Controller
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersService ordersService;

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
        List<Product> products = productService.getAll();
        Map<Integer, Integer> quantities = new java.util.HashMap<>();
        for (Product p : products) {
            String paramKey = "quantities[" + p.getId() + "]";
            String qtyStr = paramMap.getOrDefault(paramKey, "0");
            int qty = Integer.parseInt(qtyStr);
            quantities.put(p.getId(), qty);
        }

        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerName(customerName);
        request.setProductQuantities(quantities);

        OrderResultDTO result = ordersService.createOrder(request);
        model.addAttribute("message", result.getMessage());
        model.addAttribute("products", products);
        return "orderForm";
    }
}