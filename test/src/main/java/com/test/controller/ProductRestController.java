package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Product;
import com.test.service.ProductService;

@RestController
@RequestMapping("api")
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<?> getAll(){
		return ResponseEntity.ok(productService.getAll());
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<?> getOne(@PathVariable Integer id) {
		Product product = productService.getById(id);
		return ResponseEntity.ok(product);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> create(@RequestBody Product product){
		productService.createProduct(product);
		return ResponseEntity.ok("商品：" + product.getName() + "新增成功");
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id,
									@RequestBody Product product){
		productService.updateProduct(id, product);
		return ResponseEntity.ok("商品：" + product.getName() + "已修改");
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		productService.delete(id);
		return ResponseEntity.ok("已刪除");
	}
}
