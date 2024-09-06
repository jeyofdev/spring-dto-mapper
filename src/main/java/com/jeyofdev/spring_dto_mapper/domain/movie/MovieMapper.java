package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.jeyofdev.spring_dto_mapper.common.BaseDomainMapper;
import com.jeyofdev.spring_dto_mapper.domain.actor.ActorMapper;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.CategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.SaveMovieDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper implements BaseDomainMapper<Movie, MovieDTO, SaveMovieDTO> {
    private final ActorMapper actorMapper;

    @Autowired
    public MovieMapper(@Lazy ActorMapper actorMapper) {
        this.actorMapper = actorMapper;
    }

    @Override
    public MovieDTO mapFromEntity(Movie movie, boolean... args) {
        return new MovieDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getCountry(),
                movie.getYear(),
                movie.getRating(),
                movie.getSynopsys(),
                args.length > 0 && args[0] ? movie.getActorList().stream().map(actor -> actorMapper.mapFromEntity(actor, false)).toList() : null,
                args.length > 1 && args[1] ? (movie.getCategory() != null ? new CategoryDTO(movie.getCategory().getId(), movie.getCategory().getTitle(), null) : null) : null
        );
    }

    @Override
    public Movie mapToEntity(SaveMovieDTO saveMovieDTO) {
        Movie movie = new Movie();
        movie.setTitle(saveMovieDTO.title());
        movie.setCountry(saveMovieDTO.country());
        movie.setYear(saveMovieDTO.year());
        movie.setRating(saveMovieDTO.rating());
        movie.setSynopsys(saveMovieDTO.synopsys());

        return movie;
    }
}
