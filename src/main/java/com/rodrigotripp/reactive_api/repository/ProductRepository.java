package com.rodrigotripp.reactive_api.repository;

import com.rodrigotripp.reactive_api.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findByNameContaining(String name);
}