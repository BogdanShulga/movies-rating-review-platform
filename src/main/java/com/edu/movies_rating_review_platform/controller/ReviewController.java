package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<String> addReview(@RequestBody Review review) {

        String answer = reviewService.addReview(review);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(answer);
    }
}
