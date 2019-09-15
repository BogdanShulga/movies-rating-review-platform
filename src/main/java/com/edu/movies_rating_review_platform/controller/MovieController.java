package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.dto.MovieAndItReviews;
import com.edu.movies_rating_review_platform.dto.MovieIdAndRate;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/admin/movie")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable long id) {
        return movieService.removeMovie(id);
    }

    @PutMapping("/admin/movie")
    public ResponseEntity<String> updateMovie(@RequestBody Movie movie) {
        return movieService.updateMovie(movie);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieAndItReviews> getMovieAndItReviews(@PathVariable long id) {
        return movieService.getMovieAndItReviews(id);
    }

    @PatchMapping("/movie/rate")
    public ResponseEntity<String> addRate(@RequestBody MovieIdAndRate movieIdAndRate) {
        return movieService.addRate(movieIdAndRate);
    }

    @GetMapping("/movie/all")
    public ResponseEntity<List<Movie>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/stats/rating")
    public ResponseEntity<List<Movie>> getAllMoviesByRating() {
        return movieService.getAllMoviesByRating();
    }

    @GetMapping("/stats/category")
    public ResponseEntity<List<Movie>> getAllMoviesByCategory() {
        return movieService.getAllMoviesByCategory();
    }
}
