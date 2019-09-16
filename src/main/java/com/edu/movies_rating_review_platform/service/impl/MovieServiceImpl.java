package com.edu.movies_rating_review_platform.service.impl;

import com.edu.movies_rating_review_platform.dto.MovieAndItReviewsDto;
import com.edu.movies_rating_review_platform.dto.MovieIdAndRateDto;
import com.edu.movies_rating_review_platform.dto.MovieUserFriendlyDto;
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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public ResponseEntity<MovieUserFriendlyDto> addMovie(MovieUserFriendlyDto movieUserFriendlyDto) {
        Movie movie = movieUserFriendlyDto.getMovieEntity();
        Movie movie1 = movieRepository.save(movie);
        MovieUserFriendlyDto movieUserFriendlyDto1 = new MovieUserFriendlyDto(movie1);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(movieUserFriendlyDto1);
    }

    @Override
    public ResponseEntity<String> removeMovie(long id) {
        movieRepository.deleteById(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body("Movie with id: " + id + " is deleted!");
    }

    @Override
    public ResponseEntity<String> updateMovie(MovieUserFriendlyDto newMovieUserFriendlyDto) {
        Movie newMovie = newMovieUserFriendlyDto.getMovieEntity();
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
    public ResponseEntity<MovieAndItReviewsDto> getMovieAndItReviews(long id) {
        HttpStatus httpStatus;
        MovieAndItReviewsDto movieAndItReviewsDto = new MovieAndItReviewsDto();
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if (optionalMovie.isPresent()) {
            List<Review> allReviewByMovieId = reviewRepository.findAllByMovieId(id);
            MovieUserFriendlyDto movieUserFriendlyDto = new MovieUserFriendlyDto(optionalMovie.get());
            movieAndItReviewsDto.setMovieUserFriendlyDto(movieUserFriendlyDto);
            movieAndItReviewsDto.setReviews(allReviewByMovieId);
            httpStatus = HttpStatus.FOUND;
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(movieAndItReviewsDto);
    }

    @Override
    public ResponseEntity<String> addRate(MovieIdAndRateDto movieIdAndRateDto) {
        HttpStatus httpStatus;
        String answer;
        Optional<Movie> optionalMovie = movieRepository.findById(movieIdAndRateDto.getMovieUserFriendlyDtoId());
        if (optionalMovie.isPresent()) {
            Movie movie = optionalMovie.get();
            Rate rate = movie.getRate();
            rate.setVoteCount(rate.getVoteCount() + 1);
            rate.setRateValue((rate.getRateValue() + movieIdAndRateDto.getRate()) / 2);
            httpStatus = HttpStatus.OK;
            answer = "Rate added to the movie " + movie.getName() + " successfully!";
        } else {
            httpStatus = HttpStatus.NOT_FOUND;
            answer = "The movie with id " + movieIdAndRateDto.getMovieUserFriendlyDtoId() + "is not found, no update has been done!";
        }

        return ResponseEntity
                .status(httpStatus)
                .body(answer);
    }

    @Override
    public ResponseEntity<List<MovieUserFriendlyDto>> getAllMovies() {

        List<Movie> movies = movieRepository.findAll();

        List<MovieUserFriendlyDto> movieUserFriendlyDtos = new ArrayList<>();
        movies.forEach(movie -> movieUserFriendlyDtos.add(new MovieUserFriendlyDto(movie)));

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieUserFriendlyDtos);
    }

    @Override
    public ResponseEntity<List<MovieUserFriendlyDto>> getAllMoviesByRating() {

        List<Movie> movies = movieRepository.findAll();
        Comparator<Movie> comparator = Comparator.comparingDouble(movie -> movie.getRate().getRateValue());
        movies.sort(comparator.reversed());

        List<MovieUserFriendlyDto> movieUserFriendlyDtos = new ArrayList<>();
        movies.forEach(movie -> movieUserFriendlyDtos.add(new MovieUserFriendlyDto(movie)));

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieUserFriendlyDtos);
    }

    @Override
    public ResponseEntity<List<MovieUserFriendlyDto>> getAllMoviesByCategory() {

        List<Movie> movies = movieRepository.findAll();
        Comparator<Movie> comparator = Comparator.comparing(Movie::getCategory);
        movies.sort(comparator);

        List<MovieUserFriendlyDto> movieUserFriendlyDtos = new ArrayList<>();
        movies.forEach(movie -> movieUserFriendlyDtos.add(new MovieUserFriendlyDto(movie)));

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(movieUserFriendlyDtos);
    }
}
