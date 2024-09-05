package com.jeyofdev.spring_dto_mapper.domain.actor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ActorDTO (
        Long id,
        String name,
        String country,
        int age,
        String gender,
        String biography,
        List<MovieDTO> movies
) {

}
