package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.dto.MovieDto;
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

        MovieDto movieDto1 = movieService.addMovie(movieDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieDto1);
    }

    @DeleteMapping("/admin/movie/{id}")
    public ResponseEntity<String> removeMovie(@PathVariable long id) {

        String answer = movieService.removeMovie(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(answer);
    }

    @PutMapping("/admin/movie")
    public ResponseEntity<String> updateMovie(@RequestBody MovieDto newMovieDto) {

        String answer = movieService.updateMovie(newMovieDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(answer);
    }

    @GetMapping("/movie/reviews/{id}")
    public ResponseEntity<MovieReviewsDto> getMovieAndItReviews(@PathVariable long id) {

        MovieReviewsDto movieReviewsDto = movieService.getMovieAndItReviews(id);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieReviewsDto);
    }

    @PatchMapping("/movie/rate")
    public ResponseEntity<String> addRate(@RequestBody RateDto rateDto) {

        String answer = movieService.addRate(rateDto);

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
}
