package com.edu.movies_rating_review_platform.entity;

import lombok.Data;

@Data
public class Review {

    private long id;

    private long movieId;

    private String reviewMessage;

    private boolean isLiked;
}
