package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.jeyofdev.spring_dto_mapper.domain.actor.Actor;
import com.jeyofdev.spring_dto_mapper.domain.actor.ActorService;
import com.jeyofdev.spring_dto_mapper.domain.category.Category;
import com.jeyofdev.spring_dto_mapper.domain.category.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final ActorService actorService;
    private final CategoryService categoryService;

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

    public Movie addActor(Long movieId, Long actorId) {
        Movie movie = findById(movieId);
        Actor actor = actorService.findById(actorId);

        movie.getActorList().add(actor);

        return movieRepository.save(movie);
    }

    public Movie updateCategory(Long movieId, Long categoryId) {
        Movie movie = findById(movieId);
        Category category = categoryService.findById(categoryId);

        movie.setCategory(category);

        return movieRepository.save(movie);
    }

    public Movie removeActor(Long movieId, Long actorId) {
        Movie movie = findById(movieId);
        Actor actor = actorService.findById(actorId);

        movie.getActorList().remove(actor);

        return movieRepository.save(movie);
    }
}
