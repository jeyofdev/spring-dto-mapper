package com.jeyofdev.spring_dto_mapper.domain.movie.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.ActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.CategoryDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MovieDTO(
        Long id,
        String title,
        String country,
        Integer year,
        Double rating,
        String synopsys,
        List<ActorDTO> actors,
        CategoryDTO category
) {
}
