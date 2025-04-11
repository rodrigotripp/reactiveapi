package com.rodrigotripp.reactive_api.controller;

import com.rodrigotripp.reactive_api.model.User;
import com.rodrigotripp.reactive_api.service.UserApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserApiService usuarioApiService;
    
    @GetMapping
    public Flux<User> getAllUsuarios() {
        return usuarioApiService.getAllUsers();
    }
    
    @GetMapping("/{id}")
    public Mono<User> getUsuarioById(@PathVariable Long id) {
        return usuarioApiService.getUserById(id);
    }
    
    // Endpoint con transmisión en tiempo real
    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> streamAllUsuarios() {
        return usuarioApiService.getAllUsers();
    }
}