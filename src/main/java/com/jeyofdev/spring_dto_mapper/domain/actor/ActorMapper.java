package com.jeyofdev.spring_dto_mapper.domain.actor;

import com.jeyofdev.spring_dto_mapper.common.BaseDomainMapper;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.ActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.SaveActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorMapper implements BaseDomainMapper<Actor, ActorDTO, SaveActorDTO> {
    private final MovieMapper movieMapper;

    @Override
    public ActorDTO mapFromEntity(Actor actor, boolean... args) {
        return new ActorDTO(
                actor.getId(),
                actor.getName(),
                actor.getCountry(),
                actor.getAge(),
                actor.getGender(),
                actor.getBiography(),
                args.length == 1 && args[0] ?
                        actor.getMovieList().stream()
                                .map(movie -> movieMapper.mapFromEntity(movie, false, false))
                                .collect(Collectors.toList())
                        : null
        );
    }

    @Override
    public Actor mapToEntity(SaveActorDTO saveActorDTO) {
        Actor actor = new Actor();
        actor.setName(saveActorDTO.name());
        actor.setCountry(saveActorDTO.country());
        actor.setAge(saveActorDTO.age());
        actor.setGender(saveActorDTO.gender());
        actor.setBiography(saveActorDTO.biography());

        return actor;
    }
}
