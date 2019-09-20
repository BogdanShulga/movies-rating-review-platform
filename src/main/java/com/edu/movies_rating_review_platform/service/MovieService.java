package com.edu.movies_rating_review_platform.service;

import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.dto.MovieDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.enums.Category;

import java.util.List;

public interface MovieService {

    MovieDto addMovie(MovieDto movieDto);

    void removeMovie(long id);

    MovieDto updateMovie(MovieDto newMovieDto);

    MovieReviewsDto getMovieAndItReviews(long id);

    MovieDto addRate(RateDto rateDto);

    List<MovieDto> getAllMovies();

    List<MovieDto> getAllMoviesByRating();

    List<MovieDto> getAllMoviesByCategory();

    List<MovieDto> getAllMoviesWithRateGraterThen(double rateValue);

    List<Movie> getAllMoviesByEnumCategory(Category category);
}
