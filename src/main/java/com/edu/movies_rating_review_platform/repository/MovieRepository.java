package com.edu.movies_rating_review_platform.repository;

import com.edu.movies_rating_review_platform.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieRepository extends MongoRepository<Movie, Long> {
}
