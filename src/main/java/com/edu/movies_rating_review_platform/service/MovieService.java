package com.edu.movies_rating_review_platform.service;

import com.edu.movies_rating_review_platform.dto.MovieAndItReviews;
import com.edu.movies_rating_review_platform.dto.MovieIdAndRate;
import com.edu.movies_rating_review_platform.entity.Movie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MovieService {

    ResponseEntity<Movie> addMovie(Movie movie);

    ResponseEntity<String> removeMovie(long id);

    ResponseEntity<String> updateMovie(Movie movie);

    ResponseEntity<MovieAndItReviews> getMovieAndItReviews(long id);

    ResponseEntity<String> addRate(MovieIdAndRate movieIdAndRate);

    ResponseEntity<List<Movie>> getAllMovies();

    ResponseEntity<List<Movie>> getAllMoviesByRating();

    ResponseEntity<List<Movie>> getAllMoviesByCategory();
}
