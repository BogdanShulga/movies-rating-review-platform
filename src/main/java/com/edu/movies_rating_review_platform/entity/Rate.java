package com.edu.movies_rating_review_platform.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {

    private double rateValue; // 1 to 10

    private int voteCount;
}
