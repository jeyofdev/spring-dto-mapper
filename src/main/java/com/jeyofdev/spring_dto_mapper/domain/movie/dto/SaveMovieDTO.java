package com.jeyofdev.spring_dto_mapper.domain.movie.dto;

public record SaveMovieDTO(
        String title,
        String country,
        Integer year,
        Double rating,
        String synopsys
) {
}
