package br.com.movieflix.controller.response;

import lombok.Builder;

//dados que eu preciso enviar/devolver
@Builder
public record CategoryResponse(Long id, String name) {
}
