package com.edu.movies_rating_review_platform.entity;

import lombok.Data;

@Data
public class Rate {

    private int rateValue; // 1 to 10

    private int voteCount;
}
