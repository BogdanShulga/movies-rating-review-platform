package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.dto.MovieAndItReviewsDto;
import com.edu.movies_rating_review_platform.dto.MovieIdAndRateDto;
import com.edu.movies_rating_review_platform.dto.MovieUserFriendlyDto;
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
    public ResponseEntity<MovieUserFriendlyDto> addMovie(@RequestBody MovieUserFriendlyDto movieUserFriendlyDto) {
        return movieService.addMovie(movieUserFriendlyDto);
    }

    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable long id) {
        return movieService.removeMovie(id);
    }

    @PutMapping("/admin/movie")
    public ResponseEntity<String> updateMovie(@RequestBody MovieUserFriendlyDto newMovieUserFriendlyDto) {
        return movieService.updateMovie(newMovieUserFriendlyDto);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieAndItReviewsDto> getMovieAndItReviews(@PathVariable long id) {
        return movieService.getMovieAndItReviews(id);
    }

    @PatchMapping("/movie/rate")
    public ResponseEntity<String> addRate(@RequestBody MovieIdAndRateDto movieIdAndRateDto) {
        return movieService.addRate(movieIdAndRateDto);
    }

    @GetMapping("/movie/all")
    public ResponseEntity<List<MovieUserFriendlyDto>> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/stats/rating")
    public ResponseEntity<List<MovieUserFriendlyDto>> getAllMoviesByRating() {
        return movieService.getAllMoviesByRating();
    }

    @GetMapping("/stats/category")
    public ResponseEntity<List<MovieUserFriendlyDto>> getAllMoviesByCategory() {
        return movieService.getAllMoviesByCategory();
    }
}
