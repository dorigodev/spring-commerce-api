package dev.dorigo.springcommerce.api.controller.request;

public record LoginRequestDTO(
        String email,
        String password) {
}
