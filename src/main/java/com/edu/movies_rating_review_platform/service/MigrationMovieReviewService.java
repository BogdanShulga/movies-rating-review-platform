package com.edu.movies_rating_review_platform.service;

import org.springframework.http.ResponseEntity;

public interface MigrationMovieReviewService {

    ResponseEntity<String> migrate();
}
