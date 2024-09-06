package com.jeyofdev.spring_dto_mapper.domain.actor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;
import com.jeyofdev.spring_dto_mapper.enums.Gender;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ActorDTO (
        Long id,
        String name,
        String country,
        int age,
        @NotNull(message = "Gender cannot be null") Gender gender,
        String biography,
        List<MovieDTO> movies
) {

}
