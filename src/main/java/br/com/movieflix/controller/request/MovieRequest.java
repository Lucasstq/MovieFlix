package br.com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(String title,
                           String description,
                           @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
                           LocalDate realeseDate,
                           double rating,
                           List<Long> streamings,
                           List<Long> categories) {
}

