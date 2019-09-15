package com.edu.movies_rating_review_platform.service;

import com.edu.movies_rating_review_platform.entity.Review;
import org.springframework.http.ResponseEntity;

public interface ReviewService {

    ResponseEntity<String> addReview(Review review);
}
