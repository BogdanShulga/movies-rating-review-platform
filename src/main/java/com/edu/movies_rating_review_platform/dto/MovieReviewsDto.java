package com.edu.movies_rating_review_platform.dto;

import com.edu.movies_rating_review_platform.entity.Review;
import lombok.Data;

import java.util.List;

@Data
public class MovieReviewsDto {

    private MovieDto movieDto;

    private List<Review> reviews;
}
