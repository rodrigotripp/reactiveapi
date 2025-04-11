package com.rodrigotripp.reactive_api.service;

import com.rodrigotripp.reactive_api.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
public class UserApiService {

  private final WebClient webClient;

  // Inyecta la URL de la API desde la configuración
  public UserApiService(@Value("${api.usuarios.url}") String apiUrl) {
    this.webClient = WebClient.builder()
        .baseUrl(apiUrl)
        .build();
  }

  // Obtener todos los usuarios
  public Flux<User> getAllUsers() {
    return webClient.get()
        .uri("/users")
        .retrieve()
        .onStatus(status -> HttpStatus.valueOf(status.value()).is4xxClientError(),
            response -> handleError(response, "Error al obtener usuarios - Cliente"))
        .onStatus(status -> HttpStatus.valueOf(status.value()).is5xxServerError(),
            response -> handleError(response, "Error al obtener usuarios - Servidor"))
        .bodyToFlux(User.class)
        .timeout(Duration.ofSeconds(5))  // Timeout después de 5 segundos
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2)))  // 3 reintentos con 2 segundos entre cada uno
                .onErrorResume(error -> {
                    System.err.println("Error obteniendo usuarios: " + error.getMessage());
                    return Flux.empty();  // Retorna un flux vacío en caso de error
                });
  }

  // Obtener un usuario por ID
  public Mono<User> getUserById(Long id) {
    return webClient.get()
        .uri("/users/{id}", id)
        .retrieve()
        .onStatus(status -> HttpStatus.valueOf(status.value()).is4xxClientError(),
            response -> handleError(response, "Usuario no encontrado o error de cliente"))
        .onStatus(status -> HttpStatus.valueOf(status.value()).is5xxServerError(),
            response -> handleError(response, "Error del servidor al buscar usuario"))
        .bodyToMono(User.class)
        .timeout(Duration.ofSeconds(3)) // Timeout después de 3 segundos
        .retryWhen(Retry.backoff(2, Duration.ofSeconds(1))) // 2 reintentos con 1 segundo entre cada uno
        .onErrorResume(error -> {
          System.err.println("Error obteniendo usuario: " + error.getMessage());
          return Mono.empty(); // Retorna un mono vacío en caso de error
        });
  }

  // Manejo de errores
  private Mono<Throwable> handleError(ClientResponse response, String message) {
    return response.bodyToMono(String.class)
        .flatMap(errorBody -> {
          String errorMessage = message + " - Detalles: " + errorBody;
          return Mono.error(new RuntimeException(errorMessage));
        });
  }
}