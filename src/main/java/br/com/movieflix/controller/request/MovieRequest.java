package br.com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(@NotEmpty(message = "Titulo do filme é um campo obrigatório. ") String title,
                           String description,
                           @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate realeseDate,
                           double rating,
                           List<Long> streamings,
                           List<Long> categories) {
}

