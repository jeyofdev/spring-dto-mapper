package com.jeyofdev.spring_dto_mapper.domain.actor.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jeyofdev.spring_dto_mapper.enums.Gender;
import com.jeyofdev.spring_dto_mapper.exception.GenderDeserializer;
import jakarta.validation.constraints.NotNull;

public record SaveActorDTO(
        String name,
        String country,
        int age,
        @NotNull(message = "Gender cannot be null") Gender gender,
        String biography
) {

}
