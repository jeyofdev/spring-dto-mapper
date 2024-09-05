package com.jeyofdev.spring_dto_mapper.domain.movie;

import jakarta.annotation.PostConstruct;
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

    @PostConstruct
    public void init() {
        Movie movie1 = new Movie(1L, "The Godfather", "USA", 1972, 9.2, "The aging patriarch of the Corleone family moves to New York City and struggles with his personal life and loved ones.");
        Movie movie2 = new Movie(2L, "Pulp Fiction", "USA", 1994, 9.1, "The story revolves around a banker who uncovers his family's corruption and a young man who must help bring them to justice.");

        movieService.save(movie1);
        movieService.save(movie2);
    }

    @PostMapping
    public ResponseEntity<Movie> addNewMovie(@RequestBody Movie newMovie) {
        Movie createdMovie = movieService.save(newMovie);

        return new ResponseEntity<>(createdMovie, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movieList = movieService.findAll();
        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findById(movieId);
        return new ResponseEntity<>(movie, HttpStatus.FOUND);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<Movie> updateMovie(@PathVariable("movieId") Long movieId, @RequestBody Movie newMovieData) {
        Movie createdMovie = movieService.updateById(movieId, newMovieData);
        return new ResponseEntity<>(createdMovie, HttpStatus.OK);
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Long movieId) {
        String confirm = movieService.deleteById(movieId);
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }
}
