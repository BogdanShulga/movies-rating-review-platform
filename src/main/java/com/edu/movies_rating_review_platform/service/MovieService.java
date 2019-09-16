package com.edu.movies_rating_review_platform.service;

import com.edu.movies_rating_review_platform.dto.MovieAndItReviewsDto;
import com.edu.movies_rating_review_platform.dto.MovieIdAndRateDto;
import com.edu.movies_rating_review_platform.dto.MovieUserFriendlyDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseEntity<MovieUserFriendlyDto> addMovie(MovieUserFriendlyDto movieUserFriendlyDto);

    ResponseEntity<String> removeMovie(long id);

    ResponseEntity<String> updateMovie(MovieUserFriendlyDto newMovieUserFriendlyDto);

    ResponseEntity<MovieAndItReviewsDto> getMovieAndItReviews(long id);

    ResponseEntity<String> addRate(MovieIdAndRateDto movieIdAndRateDto);

    ResponseEntity<List<MovieUserFriendlyDto>> getAllMovies();

    ResponseEntity<List<MovieUserFriendlyDto>> getAllMoviesByRating();

    ResponseEntity<List<MovieUserFriendlyDto>> getAllMoviesByCategory();
}
