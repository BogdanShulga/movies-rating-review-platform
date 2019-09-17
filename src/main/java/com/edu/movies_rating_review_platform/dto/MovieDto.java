package com.edu.movies_rating_review_platform.dto;

import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.entity.Rate;
import com.edu.movies_rating_review_platform.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class MovieDto {

    private long id;

    private String name;

    private String category;

    private String director;

    private String shortDescription;

    private Rate rate;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.name = movie.getName();
        Category category = movie.getCategory();
        this.category = category.getCategoryForUser();
        this.director = movie.getDirector();
        this.shortDescription = movie.getShortDescription();
        this.rate = movie.getRate();
    }

    @JsonIgnore
    public Movie getMovieEntity() {
        Movie movie = new Movie();
        movie.setId(this.getId());
        movie.setName(this.getName());
        Category category = Category.fromString(this.getCategory());
        movie.setCategory(category);
        movie.setDirector(this.getDirector());
        movie.setShortDescription(this.getShortDescription());
        movie.setRate(this.getRate());
        return movie;
    }
}
