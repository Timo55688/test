package com.test.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dto.OrderRequestDTO;
import com.test.dto.OrderResultDTO;
import com.test.entity.Product;
import com.test.repository.ProductRepository;

@Service
public class OrdersService {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepository;

	public OrderResultDTO createOrder(OrderRequestDTO request) {
		String customerName = request.getCustomerName();
		Map<Integer, Integer> quantities = request.getProductQuantities();
		List<Product> products = productService.getAll();
		int total = 0;
		StringBuilder detail = new StringBuilder();
		StringBuilder error = new StringBuilder();

		for (Product product : products) {
			int qty = quantities.getOrDefault(product.getId(), 0);
			if (qty > 0) {
				if (qty > product.getStock()) {
					error.append(product.getName()).append("（庫存不足，剩餘").append(product.getStock()).append("）<br>");
					continue;
				}

				int subtotal = qty * product.getPrice();
				total += subtotal;

				String unit = switch (product.getName()) {
				case "蘋果" -> "顆";
				case "香蕉" -> "條";
				case "西瓜" -> "片";
				default -> "件";
				};

				detail.append(product.getName()).append(qty).append(unit).append("、");

				product.setStock(product.getStock() - qty);
				productRepository.save(product);
			}
		}

		if (error.length() > 0) {
			return new OrderResultDTO(false, customerName + "您好，以下商品庫存不足：<br>" + error);
		}

		if (detail.length() > 0) {
			detail.setLength(detail.length() - 1);
		}

		return new OrderResultDTO(true, customerName + "您好，您總共購買" + detail + "，總計" + total + "元");
	}
}
