package com.edu.movies_rating_review_platform.controller;

import com.edu.movies_rating_review_platform.service.MigrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/migrate")
public class MigrationController {

    private final MigrationService migrationService;

    @GetMapping
    public ResponseEntity<String> migrate() {

        String answer = migrationService.migrate();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(answer);
    }
}
