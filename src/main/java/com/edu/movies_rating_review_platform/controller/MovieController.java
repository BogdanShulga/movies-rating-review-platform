package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.dto.MovieDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.enums.Category;
import com.edu.movies_rating_review_platform.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping("/admin/movie")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {

        MovieDto savedMovieDto = movieService.addMovie(movieDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedMovieDto);
    }

    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable long id) {

        movieService.removeMovie(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("The movie with id = " + id + " is removed!");
    }

    @PutMapping("/admin/movie")
    public ResponseEntity<MovieDto> updateMovie(@RequestBody MovieDto newMovieDto) {

        MovieDto savedMovieDto = movieService.updateMovie(newMovieDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(savedMovieDto);
    }

    @GetMapping("/movie/reviews/{id}")
    public ResponseEntity<MovieReviewsDto> getMovieAndItReviews(@PathVariable long id) {

        MovieReviewsDto movieReviewsDto = movieService.getMovieAndItReviews(id);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieReviewsDto);
    }

    @PatchMapping("/movie/rate")
    public ResponseEntity<MovieDto> addRate(@RequestBody RateDto rateDto) {

        MovieDto answer = movieService.addRate(rateDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(answer);
    }

    @GetMapping("/movie/all")
    public ResponseEntity<List<MovieDto>> getAllMovies() {

        List<MovieDto> movieDtos = movieService.getAllMovies();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieDtos);
    }

    @GetMapping("/stats/rating")
    public ResponseEntity<List<MovieDto>> getAllMoviesByRating() {

        List<MovieDto> movieDtos = movieService.getAllMoviesByRating();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieDtos);
    }

    @GetMapping("/stats/category")
    public ResponseEntity<List<MovieDto>> getAllMoviesByCategory() {

        List<MovieDto> movieDtos = movieService.getAllMoviesByCategory();

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieDtos);
    }

    @GetMapping("/movie/{rateValue}")
    public ResponseEntity<List<MovieDto>> getAllMoviesWithRateGraterThen(@PathVariable double rateValue) {

        List<MovieDto> movieDtos = movieService.getAllMoviesWithRateGraterThen(rateValue);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieDtos);
    }

    @GetMapping("/movie/category/{category}")
    public ResponseEntity<List<Movie>> getAllMoviesByEnumCategory(@PathVariable Category category) {

        List<Movie> movies = movieService.getAllMoviesByEnumCategory(category);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movies);
    }
}
