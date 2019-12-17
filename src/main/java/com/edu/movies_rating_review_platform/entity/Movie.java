package com.edu.movies_rating_review_platform.entity;

import com.edu.movies_rating_review_platform.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private long id;

    private String name;

    private Category category;

    private String director;

    private String shortDescription;

    private Rate rate;
}
