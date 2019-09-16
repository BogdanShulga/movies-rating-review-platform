package com.edu.movies_rating_review_platform.dto;

import com.edu.movies_rating_review_platform.entity.Review;
import lombok.Data;

import java.util.List;

@Data
public class MovieAndItReviewsDto {

    private MovieUserFriendlyDto movieUserFriendlyDto;

    private List<Review> reviews;
}
