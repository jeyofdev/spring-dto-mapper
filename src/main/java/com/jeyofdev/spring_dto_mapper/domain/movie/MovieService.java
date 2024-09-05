package com.jeyofdev.spring_dto_mapper.domain.movie;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie with id " + movieId + " cannot be found"));
    }

    public Movie updateById(Long movieId, Movie updateMovieData) {
        Movie currentActor = findById(movieId);

        currentActor.setTitle(updateMovieData.getTitle());
        currentActor.setCountry(updateMovieData.getCountry());
        currentActor.setYear(updateMovieData.getYear());
        currentActor.setRating(updateMovieData.getRating());
        currentActor.setSynopsys(updateMovieData.getSynopsys());

        return movieRepository.save(currentActor);
    }

    public String deleteById(Long movieId) {
        movieRepository.deleteById(movieId);
        return "Movie with id " + movieId + " deleted";
    }
}
