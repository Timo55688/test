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
        return productRepo.findById(id).orElse(null);
    }
    
    public void createProduct(Product product) {
        product.setId(null);
        productRepo.save(product);
    }

    public void updateProduct(Integer id, Product updatedProduct) {
        Product existing = productRepo.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setStock(updatedProduct.getStock());
            productRepo.save(existing);
        }
    }
    
    public void delete(Integer id) {
        productRepo.deleteById(id);
    }
}
