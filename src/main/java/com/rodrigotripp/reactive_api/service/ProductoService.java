package com.rodrigotripp.reactive_api.service;

import com.rodrigotripp.reactive_api.model.Producto;
import com.rodrigotripp.reactive_api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {
    
    @Autowired
    private ProductoRepository productoRepository;
    
    public Flux<Producto> findAll() {
        return productoRepository.findAll();
    }
    
    public Mono<Producto> findById(String id) {
        return productoRepository.findById(id);
    }
    
    public Flux<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
    
    public Mono<Producto> save(Producto producto) {
        return productoRepository.save(producto);
    }
    
    public Mono<Void> deleteById(String id) {
        return productoRepository.deleteById(id);
    }
}