package com.rodrigotripp.reactive_api.repository;

import com.rodrigotripp.reactive_api.model.Producto;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProductoRepository extends ReactiveMongoRepository<Producto, String> {
    Flux<Producto> findByNombreContaining(String nombre);
}