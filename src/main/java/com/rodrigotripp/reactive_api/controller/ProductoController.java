package com.rodrigotripp.reactive_api.controller;

import com.rodrigotripp.reactive_api.model.Producto;
import com.rodrigotripp.reactive_api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;
    
    @GetMapping
    public Flux<Producto> getAllProductos() {
        return productoService.findAll();
    }
    
    @GetMapping("/{id}")
    public Mono<Producto> getProductoById(@PathVariable String id) {
        return productoService.findById(id);
    }
    
    @GetMapping("/search")
    public Flux<Producto> searchProductos(@RequestParam String nombre) {
        return productoService.findByNombre(nombre);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Producto> createProducto(@RequestBody Producto producto) {
        return productoService.save(producto);
    }
    
    @PutMapping("/{id}")
    public Mono<Producto> updateProducto(@PathVariable String id, @RequestBody Producto producto) {
        producto.setId(id);
        return productoService.save(producto);
    }
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProducto(@PathVariable String id) {
        return productoService.deleteById(id);
    }
    
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Producto> streamAllProductos() {
        return productoService.findAll();
    }
}