package com.edu.movies_rating_review_platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    private long id;

    private long movieId;

    private String reviewMessage;

    private boolean isLiked;
}
