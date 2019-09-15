package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.service.MigrationMovieReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MigrationMovieReviewController {

    private final MigrationMovieReviewService migrationMovieReviewService;

    @GetMapping("/migrate")
    public ResponseEntity<String> migrate() {
        return migrationMovieReviewService.migrate();
    }
}
