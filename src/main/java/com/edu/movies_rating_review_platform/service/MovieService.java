package com.edu.movies_rating_review_platform.service;

import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDto addMovie(MovieDto movieDto);

    String removeMovie(long id);

    String updateMovie(MovieDto newMovieDto);

    MovieReviewsDto getMovieAndItReviews(long id);

    String addRate(RateDto rateDto);

    List<MovieDto> getAllMovies();

    List<MovieDto> getAllMoviesByRating();

    List<MovieDto> getAllMoviesByCategory();

    List<MovieDto> getAllMoviesWithRateGraterThen(double rateValue);
}
