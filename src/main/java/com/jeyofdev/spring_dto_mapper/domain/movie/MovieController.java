package com.jeyofdev.spring_dto_mapper.domain.movie;

import com.jeyofdev.spring_dto_mapper.domain.movie.dto.MovieDTO;
import com.jeyofdev.spring_dto_mapper.domain.movie.dto.SaveMovieDTO;
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
    private final MovieMapper movieMapper;

    @PostMapping
    public ResponseEntity<MovieDTO> addNewMovie(@RequestBody SaveMovieDTO saveMovieDTO) {
        Movie movie = movieMapper.mapToEntity(saveMovieDTO);
        Movie createdMovie = movieService.save(movie);
        MovieDTO createdMovieDTO = movieMapper.mapFromEntity(createdMovie, true, true);

        return new ResponseEntity<>(createdMovieDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> movieList = movieService.findAll();
        List<MovieDTO> moviesDto = movieList.stream().map(movie -> movieMapper.mapFromEntity(movie, false, false)).toList();

        return new ResponseEntity<>(moviesDto, HttpStatus.OK);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable("movieId") Long movieId) {
        Movie movie = movieService.findById(movieId);
        MovieDTO movieDto = movieMapper.mapFromEntity(movie, true, true);

        return new ResponseEntity<>(movieDto, HttpStatus.FOUND);
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable("movieId") Long movieId, @RequestBody SaveMovieDTO saveMovieDTO) {
        Movie movie = movieMapper.mapToEntity(saveMovieDTO);
        Movie updatedMovie = movieService.updateById(movieId, movie);
        MovieDTO updatedMovieDTO = movieMapper.mapFromEntity(updatedMovie, true, true);

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
        MovieDTO movieDTO = movieMapper.mapFromEntity(movie, true, true);

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/category/{categoryId}")
    public ResponseEntity<MovieDTO> updateCategoryToMovie(@PathVariable("movieId") Long movieId, @PathVariable("categoryId") Long categoryId) {
        Movie movie = movieService.updateCategory(movieId, categoryId);
        MovieDTO movieDTO = movieMapper.mapFromEntity(movie, true, true);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PutMapping("/{movieId}/actor/{actorId}")
    public ResponseEntity<MovieDTO> removeActorToMovie(@PathVariable("movieId") Long movieId, @PathVariable("actorId") Long actorId) {
        Movie movie = movieService.removeActor(movieId, actorId);
        MovieDTO movieDTO = movieMapper.mapFromEntity(movie, true, true);

        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }
}
