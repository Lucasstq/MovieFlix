package br.com.movieflix.controller.request;

import lombok.Builder;

//dados que eu preciso receber
@Builder
public record CategoryRequest(String name) {
}
