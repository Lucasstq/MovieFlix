package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(@NotEmpty(message = "Nome do serviço streaming é um campo obrigatório. ") String name) {
}
