package com.test.service;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Product;

@Service
public class OrdersService {

	@Autowired
	private ProductService productService;

	public String createOrder(String customerName, Map<String, String> paramMap) {
		List<Product> products = productService.getAll();
		int total = 0;
		StringBuilder detail = new StringBuilder();

		for (Product product : products) {
			String key = "quantities[" + product.getId() + "]";
			if (paramMap.containsKey(key)) {
				int qty = Integer.parseInt(paramMap.get(key));
				if (qty > 0) {
					int subtotal = qty * product.getPrice();
					total += subtotal;

					String unit = switch (product.getName()) {
					case "蘋果", "鳳梨" -> "顆";
					case "香蕉" -> "條";
					case "西瓜" -> "片";
					default -> "件";
					};

					detail.append(product.getName()).append(qty).append(unit).append("、");
				}
			}
		}

		if (detail.length() > 0) {
			detail.setLength(detail.length() - 1);
		}
		return customerName + "你好，您總共購買" + detail + "，總計" + total + "元";
	}
}
