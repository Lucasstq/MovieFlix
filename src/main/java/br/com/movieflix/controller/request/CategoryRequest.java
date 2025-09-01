package br.com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

//dados que eu preciso receber
@Builder
public record CategoryRequest(@NotEmpty(message = "Nome da categoria é um campo obrigatório. ") String name) {
}
