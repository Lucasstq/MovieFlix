package br.com.movieflix.controller.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(String title,
                           String description,
                           LocalDate realeseDate,
                           double rating,
                           List<Long> streamings,
                           List<Long> categories) {
}

