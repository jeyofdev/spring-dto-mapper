package com.jeyofdev.spring_dto_mapper.domain.category.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CategoryDTO(
        Long id,
        String title,
        List<MovieDTO> movies
) {
}
