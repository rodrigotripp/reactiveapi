package com.rodrigotripp.reactive_api.controller;

import com.rodrigotripp.reactive_api.model.Product;
import com.rodrigotripp.reactive_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.findAll();
    }
    
    @GetMapping("/{id}")
    public Mono<Product> getProductById(@PathVariable String id) {
        return productService.findById(id);
    }
    
    @GetMapping("/search")
    public Flux<Product> searchProducts(@RequestParam String name) {
        return productService.findByName(name);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Product> createProduct(@RequestBody Product product) {
        return productService.save(product);
    }
    
    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        return productService.save(product);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable String id) {
        return productService.deleteById(id);
    }
    
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Product> streamAllProducts() {
        return productService.findAll();
    }
}