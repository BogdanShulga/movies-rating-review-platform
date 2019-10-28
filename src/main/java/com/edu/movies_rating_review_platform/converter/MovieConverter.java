package com.edu.movies_rating_review_platform.converter;

import com.edu.movies_rating_review_platform.dto.MovieDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.enums.Category;

public class MovieConverter {

    public static MovieDto getMovieDto(Movie movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setId(movie.getId());
        movieDto.setName(movie.getName());
        Category category = movie.getCategory();
        movieDto.setCategory(category.getCategory());
        movieDto.setDirector(movie.getDirector());
        movieDto.setShortDescription(movie.getShortDescription());
        movieDto.setRate(movie.getRate());
        return movieDto;
    }

    public static Movie getMovieEntity(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setId(movieDto.getId());
        movie.setName(movieDto.getName());
        Category category = Category.fromString(movieDto.getCategory());
        movie.setCategory(category);
        movie.setDirector(movieDto.getDirector());
        movie.setShortDescription(movieDto.getShortDescription());
        movie.setRate(movieDto.getRate());
        return movie;
    }
}
