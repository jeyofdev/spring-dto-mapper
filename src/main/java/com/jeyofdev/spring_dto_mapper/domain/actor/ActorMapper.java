package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.jeyofdev.spring_dto_mapper.domain.actor.dto.ActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.SaveActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieMapper;

import java.util.stream.Collectors;

public class ActorMapper {
    public static ActorDTO mapFromEntity(Actor actor, boolean includeRelation) {
        return new ActorDTO(
                actor.getId(),
                actor.getName(),
                actor.getCountry(),
                actor.getAge(),
                actor.getGender(),
                actor.getBiography(),
                includeRelation ?
                        actor.getMovieList().stream()
                                .map(movie -> MovieMapper.mapFromEntity(movie, false, false))
                                .collect(Collectors.toList())
                        : null
        );
    }

    public static Actor mapToEntity(SaveActorDTO saveActorDTO) {
        Actor actor = new Actor();
        actor.setName(saveActorDTO.name());
        actor.setCountry(saveActorDTO.country());
        actor.setAge(saveActorDTO.age());
        actor.setGender(saveActorDTO.gender());
        actor.setBiography(saveActorDTO.biography());

        return actor;
    }
}
