package com.edu.movies_rating_review_platform.service.impl;

import com.edu.movies_rating_review_platform.converter.MovieConverter;
import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.dto.MovieDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.entity.Rate;
import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.enums.Category;
import com.edu.movies_rating_review_platform.exception.NotFoundException;
import com.edu.movies_rating_review_platform.repository.MovieRepository;
import com.edu.movies_rating_review_platform.repository.ReviewRepository;
import com.edu.movies_rating_review_platform.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final ReviewRepository reviewRepository;

    @Override
    public MovieDto addMovie(MovieDto movieDto) {

        Movie movie = MovieConverter.getMovieEntity(movieDto);
        Movie savedMovie = movieRepository.save(movie);

        return MovieConverter.getMovieDto(savedMovie);
    }

    @Override
    public void removeMovie(long id) {

        movieRepository.deleteById(id);
    }

    @Override
    public MovieDto updateMovie(MovieDto newMovieDto) {

        movieRepository.findById(newMovieDto.getId()).orElseThrow(NotFoundException::new);

        Movie newMovie = MovieConverter.getMovieEntity(newMovieDto);

        return MovieConverter.getMovieDto(movieRepository.save(newMovie));
    }

    @Override
    public MovieReviewsDto getMovieAndItReviews(long id) {

        Movie existingMovie = movieRepository.findById(id).orElseThrow(NotFoundException::new);

        MovieDto existingMovieDto = MovieConverter.getMovieDto(existingMovie);
        List<Review> allReviewByMovieId = reviewRepository.findAllByMovieId(id);

        MovieReviewsDto movieReviewsDto = new MovieReviewsDto();
        movieReviewsDto.setMovieDto(existingMovieDto);
        movieReviewsDto.setReviews(allReviewByMovieId);

        return movieReviewsDto;
    }

    @Override
    public MovieDto addRate(RateDto rateDto) {

        Movie existingMovie = movieRepository.findById(rateDto.getMovieDtoId()).orElseThrow(NotFoundException::new);

        Rate rate = existingMovie.getRate();
        rate.setVoteCount(rate.getVoteCount() + 1);
        rate.setRateValue((rate.getRateValue() + rateDto.getRate()) / 2);
        existingMovie.setRate(rate);

        Movie updatedMovie = movieRepository.save(existingMovie);

        return MovieConverter.getMovieDto(updatedMovie);
    }

    @Override
    public List<MovieDto> getAllMovies() {

        List<Movie> movies = movieRepository.findAll();

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(MovieConverter.getMovieDto(movie)));

        return movieDtos;
    }

    @Override
    public List<MovieDto> getAllMoviesByRating() {

        List<Movie> movies = movieRepository.findAllOrderedBy(new Sort(Sort.Direction.DESC, "rate.rateValue"));

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(MovieConverter.getMovieDto(movie)));

        return movieDtos;
    }

    @Override
    public List<MovieDto> getAllMoviesByCategory() {

        List<Movie> movies = movieRepository.findAllOrderedBy(new Sort(Sort.Direction.ASC, "category"));

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(MovieConverter.getMovieDto(movie)));

        return movieDtos;
    }

    @Override
    public List<MovieDto> getAllMoviesWithRateGraterThen(double rateValue) {

        List<Movie> movies = movieRepository.findByRateRateValueGreaterThanQuery(rateValue);

        List<MovieDto> movieDtos = new ArrayList<>();
        movies.forEach(movie -> movieDtos.add(MovieConverter.getMovieDto(movie)));

        return movieDtos;
    }

    @Override
    public List<Movie> getAllMoviesByEnumCategory(Category category) {

        return movieRepository.findAllByCategory(category);
    }
}
