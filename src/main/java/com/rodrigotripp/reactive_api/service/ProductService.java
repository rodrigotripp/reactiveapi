package com.rodrigotripp.reactive_api.service;

import com.rodrigotripp.reactive_api.model.Product;
import com.rodrigotripp.reactive_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    public Flux<Product> findAll() {
        return productRepository.findAll();
    }
    
    public Mono<Product> findById(String id) {
        return productRepository.findById(id);
    }
    
    public Flux<Product> findByName(String name) {
        return productRepository.findByNameContaining(name);
    }
    
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }
    
    public Mono<Void> deleteById(String id) {
        return productRepository.deleteById(id);
    }
}