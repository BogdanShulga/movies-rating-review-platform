package com.edu.movies_rating_review_platform.service.impl;

import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.dto.MovieDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.entity.Rate;
import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.exception.NotFoundExceptions;
import com.edu.movies_rating_review_platform.repository.MovieRepository;
import com.edu.movies_rating_review_platform.repository.ReviewRepository;
import com.edu.movies_rating_review_platform.service.MovieService;
import lombok.RequiredArgsConstructor;
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
    public MovieDto addMovie(MovieDto movieDto) {

        Movie movie = movieDto.getMovieEntity();
        Movie movie1 = movieRepository.save(movie);

        return new MovieDto(movie1);
    }

    @Override
    public String removeMovie(long id) {

        movieRepository.deleteById(id);
        return "Movie with id: " + id + " is deleted!";
    }

    @Override
    public String updateMovie(MovieDto newMovieDto) {

        Movie newMovie = newMovieDto.getMovieEntity();

        Optional<Movie> oldMovieOptional = movieRepository.findById(newMovie.getId());

        oldMovieOptional.ifPresentOrElse(movie -> movieRepository.save(newMovie), () -> {
            throw new NotFoundExceptions();
        });

        return "The movie \"" + newMovie.getName() + "\" is updated!";
    }

    @Override
    public MovieReviewsDto getMovieAndItReviews(long id) {

        Optional<Movie> optionalMovie = movieRepository.findById(id);
        Movie movie = optionalMovie.orElseThrow(NotFoundExceptions::new);

        MovieDto movieDto = new MovieDto(movie);
        List<Review> allReviewByMovieId = reviewRepository.findAllByMovieId(id);

        MovieReviewsDto movieReviewsDto = new MovieReviewsDto();
        movieReviewsDto.setMovieDto(movieDto);
        movieReviewsDto.setReviews(allReviewByMovieId);

        return movieReviewsDto;
    }

    @Override
    public String addRate(RateDto rateDto) {

        Optional<Movie> optionalMovie = movieRepository.findById(rateDto.getMovieUserFriendlyDtoId());
        Movie movie = optionalMovie.orElseThrow(NotFoundExceptions::new);

        Rate rate = movie.getRate();
        rate.setVoteCount(rate.getVoteCount() + 1);
        rate.setRateValue((rate.getRateValue() + rateDto.getRate()) / 2);

        movie.setRate(rate);
        Movie movie1 = movieRepository.save(movie);

        return "Rate added to the movie \"" + movie1.getName() + "\" successfully!";
    }

    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movies = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(new MovieDto(movie)));

        return movieDtos;
    }

    @Override
    public List<MovieDto> getAllMoviesByRating() {

        List<Movie> movies = movieRepository.findAll();
        Comparator<Movie> comparator = Comparator.comparingDouble(movie -> movie.getRate().getRateValue());
        movies.sort(comparator.reversed());

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(new MovieDto(movie)));

        return movieDtos;
    }

    @Override
    public List<MovieDto> getAllMoviesByCategory() {

        List<Movie> movies = movieRepository.findAll();
        Comparator<Movie> comparator = Comparator.comparing(Movie::getCategory);
        movies.sort(comparator);

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(new MovieDto(movie)));

        return movieDtos;
    }
}
