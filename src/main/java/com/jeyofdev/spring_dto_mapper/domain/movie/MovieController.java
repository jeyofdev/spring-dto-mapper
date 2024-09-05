package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.SaveMovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    /*@PostConstruct
    public void init() {
        Movie movie1 = new Movie(1L, "The Godfather", "USA", 1972, 9.2, "The aging patriarch of the Corleone family moves to New York City and struggles with his personal life and loved ones.");
        Movie movie2 = new Movie(2L, "Pulp Fiction", "USA", 1994, 9.1, "The story revolves around a banker who uncovers his family's corruption and a young man who must help bring them to justice.");

        movieService.save(movie1);
        movieService.save(movie2);
    }*/

    @PostMapping
    public ResponseEntity<MovieDTO> addNewMovie(@RequestBody SaveMovieDto saveMovieDTO) {
        Movie movie = MovieMapper.mapToEntity(saveMovieDTO);
        Movie createdMovie = movieService.save(movie);
        MovieDTO createdMovieDTO = MovieMapper.mapFromEntity(createdMovie, true, true);

        return new ResponseEntity<>(createdMovieDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> movieList = movieService.findAll();
        List<MovieDTO> moviesDto = movieList.stream().map(movie -> MovieMapper.mapFromEntity(movie, false, false)).toList();

        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findById(movieId);
        MovieDTO movieDto = MovieMapper.mapFromEntity(movie, true, true);

        return new ResponseEntity<>(movieDto, HttpStatus.FOUND);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable("movieId") Long movieId, @RequestBody SaveMovieDto saveMovieDTO) {
        Movie movie = MovieMapper.mapToEntity(saveMovieDTO);
        Movie updatedMovie = movieService.updateById(movieId, movie);
        MovieDTO updatedMovieDTO = MovieMapper.mapFromEntity(updatedMovie, true, true);

        return new ResponseEntity<>(updatedMovieDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Long movieId) {
        String confirm = movieService.deleteById(movieId);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }

    @PostMapping("/{movieId}/actor/{actorId}")
    public ResponseEntity<MovieDTO> addActorToMovie(@PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId) {
        Movie movie = movieService.addActor(movieId, actorId);
        MovieDTO movieDTO = MovieMapper.mapFromEntity(movie, true, true);

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/category/{categoryId}")
    public ResponseEntity<MovieDTO> updateCategoryToMovie(@PathVariable("movieId") Long movieId, @PathVariable("categoryId") Long categoryId) {
        Movie movie = movieService.updateCategory(movieId, categoryId);
        MovieDTO movieDTO = MovieMapper.mapFromEntity(movie, true, true);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/actor/{actorId}")
    public ResponseEntity<MovieDTO> removeActorToMovie(@PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId) {
        Movie movie = movieService.removeActor(movieId, actorId);
        MovieDTO movieDTO = MovieMapper.mapFromEntity(movie, true, true);

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }
}
