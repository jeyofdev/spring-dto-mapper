package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.jeyofdev.spring_dto_mapper.common.AbstractDomainService;
import com.jeyofdev.spring_dto_mapper.domain.actor.Actor;
import com.jeyofdev.spring_dto_mapper.domain.actor.ActorService;
import com.jeyofdev.spring_dto_mapper.domain.category.Category;
import com.jeyofdev.spring_dto_mapper.domain.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService extends AbstractDomainService<Movie> {
    private final MovieRepository movieRepository;
    private final ActorService actorService;
    private final CategoryService categoryService;

    @Autowired
    public MovieService(MovieRepository movieRepository, ActorService actorService, CategoryService categoryService) {
        super(movieRepository, "movie");
        this.movieRepository = movieRepository;
        this.actorService = actorService;
        this.categoryService = categoryService;
    }

    @Override
    public Movie updateById(Long movieId, Movie updateMovieData) {
        Movie currentActor = findById(movieId);

        currentActor.setTitle(updateMovieData.getTitle());
        currentActor.setCountry(updateMovieData.getCountry());
        currentActor.setYear(updateMovieData.getYear());
        currentActor.setRating(updateMovieData.getRating());
        currentActor.setSynopsys(updateMovieData.getSynopsys());

        return movieRepository.save(currentActor);
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
