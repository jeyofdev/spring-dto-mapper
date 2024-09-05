package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.jeyofdev.spring_dto_mapper.domain.actor.ActorMapper;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.CategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.SaveMovieDto;

import java.util.ArrayList;

public class MovieMapper {
    public static MovieDTO mapFromEntity(Movie movie, boolean includeActors, boolean includeCategory) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getCountry(),
                movie.getYear(),
                movie.getRating(),
                movie.getSynopsys(),
                includeActors ? movie.getActorList().stream().map(actor -> ActorMapper.mapFromEntity(actor, false)).toList() : null,
                includeCategory ? (movie.getCategory() != null ? new CategoryDTO(movie.getCategory().getId(), movie.getCategory().getTitle(), null) : null) : null
        );
    }

    public static Movie mapToEntity(SaveMovieDto saveMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(saveMovieDTO.title());
        movie.setCountry(saveMovieDTO.country());
        movie.setYear(saveMovieDTO.year());
        movie.setRating(saveMovieDTO.rating());
        movie.setSynopsys(saveMovieDTO.synopsys());

        return movie;
    }
}
