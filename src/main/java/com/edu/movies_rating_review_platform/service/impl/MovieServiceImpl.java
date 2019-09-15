package com.edu.movies_rating_review_platform.service.impl;

import com.edu.movies_rating_review_platform.dto.MovieAndItReviews;
import com.edu.movies_rating_review_platform.dto.MovieIdAndRate;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.entity.Rate;
import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.repository.MovieRepository;
import com.edu.movies_rating_review_platform.repository.ReviewRepository;
import com.edu.movies_rating_review_platform.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<Movie> addMovie(Movie movie) {
        Movie movie1 = movieRepository.save(movie);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movie1);
    }

    @Override
    public ResponseEntity<String> removeMovie(long id) {
        movieRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Movie with id: " + id + " is deleted!");
    }

    @Override
    public ResponseEntity<String> updateMovie(Movie newMovie) {
        String answer;
        HttpStatus httpStatus;
        Optional<Movie> oldMovieOptional = movieRepository.findById(newMovie.getId());
        if (oldMovieOptional.isPresent()) {
            movieRepository.save(newMovie);
            httpStatus = HttpStatus.OK;
            answer = "The movie is updated!";
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            answer = "The movie " + newMovie.getName() + "is not found, no update has been done!";
        }
        return ResponseEntity
                .status(httpStatus)
                .body(answer);
    }

    @Override
    public ResponseEntity<MovieAndItReviews> getMovieAndItReviews(long id) {
        HttpStatus httpStatus;
        MovieAndItReviews movieAndItReviews = new MovieAndItReviews();
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            List<Review> allReviewByMovieId = reviewRepository.findAllByMovieId(id);
            movieAndItReviews.setMovie(optionalMovie.get());
            movieAndItReviews.setReviews(allReviewByMovieId);
            httpStatus = HttpStatus.FOUND;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(movieAndItReviews);
    }

    @Override
    public ResponseEntity<String> addRate(MovieIdAndRate movieIdAndRate) {
        HttpStatus httpStatus;
        String answer;
        Optional<Movie> optionalMovie = movieRepository.findById(movieIdAndRate.getMovieId());
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            Rate rate = movie.getRate();
            rate.setVoteCount(rate.getVoteCount() + 1);
            rate.setRateValue((rate.getRateValue() + movieIdAndRate.getRate()) / 2);
            httpStatus = HttpStatus.OK;
            answer = "Rate added to the movie " + movie.getName() + " successfully!";
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            answer = "The movie with id " + movieIdAndRate.getMovieId() + "is not found, no update has been done!";
        }

        return ResponseEntity
                .status(httpStatus)
                .body(answer);
    }

    @Override
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movies);
    }

    @Override
    public ResponseEntity<List<Movie>> getAllMoviesByRating() {
        List<Movie> movies = movieRepository.findAll();
        Comparator<Movie> comparator = Comparator.comparingInt(movie -> movie.getRate().getRateValue());
        movies.sort(comparator.reversed());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movies);
    }

    @Override
    public ResponseEntity<List<Movie>> getAllMoviesByCategory() {
        List<Movie> movies = movieRepository.findAll();
        Comparator<Movie> comparator = Comparator.comparing(Movie::getCategory);
        movies.sort(comparator);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movies);
    }
}
