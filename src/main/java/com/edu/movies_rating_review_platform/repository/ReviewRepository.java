package com.edu.movies_rating_review_platform.repository;

import com.edu.movies_rating_review_platform.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, Long> {

    List<Review> findAllByMovieId(long movieId);
}
