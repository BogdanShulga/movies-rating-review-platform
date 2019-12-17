package com.edu.movies_rating_review_platform.dto;

import com.edu.movies_rating_review_platform.entity.Rate;
import lombok.Data;

@Data
public class MovieDto {

    private long id;

    private String name;

    private String category;

    private String director;

    private String shortDescription;

    private Rate rate;
}
