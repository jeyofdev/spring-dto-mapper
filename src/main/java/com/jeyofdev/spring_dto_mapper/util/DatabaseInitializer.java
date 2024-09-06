package com.jeyofdev.spring_dto_mapper.util;

import com.jeyofdev.spring_dto_mapper.domain.actor.Actor;
import com.jeyofdev.spring_dto_mapper.domain.actor.ActorMapper;
import com.jeyofdev.spring_dto_mapper.domain.actor.ActorService;
import com.jeyofdev.spring_dto_mapper.domain.actor.dto.SaveActorDTO;
import com.jeyofdev.spring_dto_mapper.domain.category.*;
import com.jeyofdev.spring_dto_mapper.domain.category.dto.SaveCategoryDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.Movie;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieMapper;
import com.jeyofdev.spring_dto_mapper.domain.movie.MovieService;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.SaveMovieDTO;
import com.jeyofdev.spring_dto_mapper.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {
    private final CategoryService categoryService;
    private final ActorService actorService;
    private final MovieService movieService;
    private final ActorMapper actorMapper;
    private final CategoryMapper categoryMapper;
    private final MovieMapper movieMapper;

    @Override
    public void run(String... args) throws Exception {

        // add some categories
        SaveCategoryDTO saveCategoryDTO1 = new SaveCategoryDTO("Thriller");
        SaveCategoryDTO saveCategoryDTO2 = new SaveCategoryDTO("Drama");

        Category category1 = categoryMapper.mapToEntity(saveCategoryDTO1);
        Category category2 = categoryMapper.mapToEntity(saveCategoryDTO2);

        categoryService.save(category1);
        categoryService.save(category2);

        // Add some actors
        SaveActorDTO saveActorDTO1 = new SaveActorDTO("Al Pacino", "USA", 84, Gender.MALE, "Alfredo James Pacino (born April 25, 1940) is an American actor and filmmaker.");
        SaveActorDTO saveActorDTO2 = new SaveActorDTO("John Travolta", "USA", 70, Gender.MALE, "John Joseph Travolta (born February 18, 1954) is an American actor, film producer, dancer, and singer.");
        SaveActorDTO saveActorDTO3 = new SaveActorDTO("Uma Thurman", "USA", 54, Gender.FEMALE, "Uma Karuna Thurman (née le 29 avril 1970) est une actrice et mannequin américaine.");

        Actor actor1 = actorMapper.mapToEntity(saveActorDTO1);
        Actor actor2 = actorMapper.mapToEntity(saveActorDTO2);
        Actor actor3 = actorMapper.mapToEntity(saveActorDTO3);

        actorService.save(actor1);
        actorService.save(actor2);
        actorService.save(actor3);

        // add some movies
        SaveMovieDTO saveMovieDTO1 = new SaveMovieDTO("The Godfather", "USA", 1972, 9.2, "The aging patriarch of the Corleone family moves to New York City and struggles with his personal life and loved ones.");
        SaveMovieDTO saveMovieDTO2 = new SaveMovieDTO("Pulp Fiction", "USA", 1994, 9.1, "The story revolves around a banker who uncovers his family's corruption and a young man who must help bring them to justice.");

        Movie movie1 = movieMapper.mapToEntity(saveMovieDTO1);
        Movie movie2 = movieMapper.mapToEntity(saveMovieDTO2);

        movieService.save(movie1);
        movieService.save(movie2);

        // add actors to movies
        movie1.setCategory(category1);
        movie2.setCategory(category2);

        // add category to movie
        movie1.getActorList().add(actor1);
        movie2.getActorList().add(actor2);
        movie2.getActorList().add(actor3);

        // save all movies again with updated actors and categories
        movieService.save(movie1);
        movieService.save(movie2);
    }
}
