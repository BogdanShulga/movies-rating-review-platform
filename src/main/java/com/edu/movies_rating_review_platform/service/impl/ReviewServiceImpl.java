package com.edu.movies_rating_review_platform.service.impl;

import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.repository.ReviewRepository;
import com.edu.movies_rating_review_platform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<String> addReview(Review review) {
        reviewRepository.save(review);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Review is added!");
    }
}
