package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Product;
import com.test.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productRepo;

    public List<Product> getAll() {
        return productRepo.findAll();
    }

    public Product getById(Integer id) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
        	throw new IllegalArgumentException("找不到編號為：" + id + "的商品");
        }
    	return product;
    }
    
    public void createProduct(Product product) {
    	Product existing = productRepo.findByName(product.getName()).orElse(null);
        if (existing != null) {
            throw new IllegalArgumentException("商品名稱已存在：" + product.getName());
        }
    	product.setId(null);
        productRepo.save(product);
    }

    public void updateProduct(Integer id, Product updatedProduct) {
        Product product = productRepo.findById(id).orElse(null);
        if (product == null) {
            throw new IllegalArgumentException("找不到編號為：" + id + "的商品");
        }
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());
        productRepo.save(product);
    }
    
    public void delete(Integer id) {
        productRepo.deleteById(id);
    }
}
