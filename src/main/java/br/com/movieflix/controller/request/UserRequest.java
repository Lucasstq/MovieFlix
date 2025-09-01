package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserRequest(
        @NotBlank(message = "Campo nome é obrigatório.")
        String name,

        @NotBlank(message = "Campo email é obrigatório.")
        String email,

        @NotBlank(message = "Campo senha é obrigatório.")
        String password) {
}
