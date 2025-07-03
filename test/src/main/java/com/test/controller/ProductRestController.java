package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
		try {
			Product product = productService.getById(id);
			return ResponseEntity.ok(product);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> create(@RequestBody Product product){
		try {
			productService.createProduct(product);
			return ResponseEntity.ok("商品：" + product.getName() + "新增成功");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/product/{id}")
	public ResponseEntity<?> update(@PathVariable Integer id,
									@RequestBody Product product){
		try {
			productService.updateProduct(id, product);
			return ResponseEntity.ok("商品：" + product.getName() + "已修改");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		productService.delete(id);
		return ResponseEntity.ok("已刪除");
	}
}
