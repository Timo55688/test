package com.test.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.test.dto.OrderRequestDTO;
import com.test.dto.OrderResultDTO;
import com.test.entity.Product;
import com.test.repository.OrderRepository;
import com.test.repository.ProductRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class OrdersServiceTest {

    @Autowired
    private OrdersService ordersService;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    void testCreateOrder() {

        Product apple = new Product();
        apple.setId(1);
        apple.setName("蘋果");
        apple.setPrice(10);
        apple.setStock(3);
        
        when(productService.getAll()).thenReturn(List.of(apple));
        
        Map<Integer, Integer> quantities = new HashMap<>();
        quantities.put(1, 2);
        OrderRequestDTO request = new OrderRequestDTO("測試用", quantities);

        OrderResultDTO result = ordersService.createOrder(request);

        assertTrue(result.isSuccess());
        assertTrue(result.getMessage().contains("測試用您好，您總共購買蘋果2顆"));
        assertTrue(result.getMessage().contains("總計20元"));
        System.out.println("success");
        System.out.println(result.getMessage());

    }
}
