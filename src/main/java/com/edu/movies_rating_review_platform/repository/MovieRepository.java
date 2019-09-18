package com.edu.movies_rating_review_platform.repository;

import com.edu.movies_rating_review_platform.entity.Movie;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, Long> {

    List<Movie> findAllOrderedBy(Sort sort);

    @Query("{'rate.rateValue' : {$gt : ?0}}")
    List<Movie> findByRateRateValueGreaterThanQuery(double rateValue);
}
