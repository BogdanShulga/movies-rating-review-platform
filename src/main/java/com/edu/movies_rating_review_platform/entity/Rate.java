package com.edu.movies_rating_review_platform.entity;

import lombok.Data;

@Data
public class Rate {

    private long id;

    private int rate; // 1 to 10

    private int voteCount;
}
