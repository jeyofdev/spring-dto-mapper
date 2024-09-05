package com.jeyofdev.spring_dto_mapper.domain.actor.dto;

public record SaveActorDTO(
        String name,
        String country,
        int age,
        String gender,
        String biography
) {

}
