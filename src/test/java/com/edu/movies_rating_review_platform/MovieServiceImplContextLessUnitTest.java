package com.edu.movies_rating_review_platform;

import com.edu.movies_rating_review_platform.converter.MovieConverter;
import com.edu.movies_rating_review_platform.dto.MovieDto;
import com.edu.movies_rating_review_platform.dto.MovieReviewsDto;
import com.edu.movies_rating_review_platform.dto.RateDto;
import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.entity.Rate;
import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.enums.Category;
import com.edu.movies_rating_review_platform.exception.NotFoundException;
import com.edu.movies_rating_review_platform.repository.MovieRepository;
import com.edu.movies_rating_review_platform.repository.ReviewRepository;
import com.edu.movies_rating_review_platform.service.impl.MigrationServiceImpl;
import com.edu.movies_rating_review_platform.service.impl.MovieServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplContextLessUnitTest {

    @Mock
    MovieRepository movieRepositoryMock;

    @Mock
    ReviewRepository reviewRepositoryMock;

    @InjectMocks
    MovieServiceImpl movieService;

    @InjectMocks
    MigrationServiceImpl migrationService;

    Movie testMovie = new Movie(1L,
            "Spider-Man: Far from Home",
            Category.ACT,
            "Jon Watts",
            "In this adventure, Spider-man faces new challenges after Avengers: Endgame.",
            new Rate(7.9, 532));

    MovieDto testMovieDto = MovieConverter.getMovieDto(testMovie);

    @Test
    public void addMovieTest() {

        when(movieRepositoryMock.save(testMovie)).thenReturn(testMovie);

        assertEquals(testMovieDto, movieService.addMovie(testMovieDto));

        verify(movieRepositoryMock, times(1)).save(any());
    }

    @Test
    public void removeMovieTest() {

        doNothing().when(movieRepositoryMock).deleteById(anyLong());

        movieService.removeMovie(15L);

        verify(movieRepositoryMock, times(1)).deleteById(15L);
    }

    @Test
    public void updateMovieTest() {

        when(movieRepositoryMock.findById(testMovieDto.getId())).thenReturn(Optional.of(testMovie));

        when(movieRepositoryMock.save(testMovie)).thenReturn(testMovie);

        assertEquals(testMovieDto, movieService.updateMovie(testMovieDto));

        verify(movieRepositoryMock, times(1)).findById(testMovieDto.getId());

        verify(movieRepositoryMock, times(1)).save(testMovie);
    }

    @Test(expected = NotFoundException.class)
    public void updateMovieExceptionTest() {

        when(movieRepositoryMock.findById(anyLong())).thenThrow(new NotFoundException());

        when(movieRepositoryMock.save(testMovie)).thenReturn(testMovie);

        movieService.updateMovie(testMovieDto);

        verify(movieRepositoryMock, times(1)).findById(testMovieDto.getId());

        verify(movieRepositoryMock, never()).save(testMovie);
    }

    @Test
    public void getMovieAndItReviewsTest() {

        List<Review> testReviews = migrationService.generateReview();

        long id = testMovieDto.getId();

        MovieReviewsDto expectedTestMovieReviewsDto = new MovieReviewsDto();
        expectedTestMovieReviewsDto.setMovieDto(testMovieDto);
        expectedTestMovieReviewsDto.setReviews(testReviews.stream()
                .filter(review -> review.getMovieId() == id)
                .collect(Collectors.toList()));

        when(movieRepositoryMock.findById(id)).thenReturn(Optional.of(testMovie));

        when(reviewRepositoryMock.findAllByMovieId(id)).thenReturn(expectedTestMovieReviewsDto.getReviews());

        assertEquals(expectedTestMovieReviewsDto, movieService.getMovieAndItReviews(id));

        verify(movieRepositoryMock, times(1)).findById(id);

        verify(reviewRepositoryMock, times(1)).findAllByMovieId(id);
    }

    @Test
    public void addRateTest() {

        long id = testMovieDto.getId();

        when(movieRepositoryMock.save(testMovie)).thenReturn(testMovie);

        when(movieRepositoryMock.findById(id)).thenReturn(Optional.of(testMovie));

        Rate testRate = testMovieDto.getRate();

        RateDto testRateDto = new RateDto();
        testRateDto.setRate(7);
        testRateDto.setMovieDtoId(testMovieDto.getId());

        MovieDto expectedMovieDto = movieService.addRate(testRateDto);

        testRate.setVoteCount(testRate.getVoteCount() + 1);
        testRate.setRateValue((testRate.getRateValue() + testRateDto.getRate()) / 2);

        testMovieDto.setRate(testRate);

        assertEquals(testMovieDto, expectedMovieDto);

        verify(movieRepositoryMock, times(1)).findById(id);

        verify(movieRepositoryMock, times(1)).save(testMovie);
    }

    @Test
    public void getAllMoviesTest() {

        List<Movie> testMovies = migrationService.generateMovies();

        List<MovieDto> expectedTestMovieDtos = new ArrayList<>();
        testMovies.forEach(movie -> expectedTestMovieDtos.add(MovieConverter.getMovieDto(movie)));

        when(movieRepositoryMock.findAll()).thenReturn(testMovies);

        assertEquals(expectedTestMovieDtos, movieService.getAllMovies());

        verify(movieRepositoryMock, times(1)).findAll();
    }

    @Test
    public void getAllMoviesByRatingTest() {

        List<Movie> testMovies = migrationService.generateMovies();

        List<Movie> expectedMoviesByRating = testMovies.stream()
                .sorted((o1, o2) -> (int) (o2.getRate().getRateValue() - o1.getRate().getRateValue()))
                .collect(Collectors.toList());

        List<MovieDto> expectedMovieDtosByRating = new ArrayList<>();
        expectedMoviesByRating.forEach(movie -> expectedMovieDtosByRating.add(MovieConverter.getMovieDto(movie)));

        when(movieRepositoryMock.findAllOrderedBy(new Sort(Sort.Direction.DESC, "rate.rateValue")))
                .thenReturn(expectedMoviesByRating);

        assertEquals(expectedMovieDtosByRating, movieService.getAllMoviesByRating());

        verify(movieRepositoryMock,times(1))
                .findAllOrderedBy(new Sort(Sort.Direction.DESC, "rate.rateValue"));
    }

    @Test
    public void getAllMoviesByCategoryTest() {

        List<Movie> testMovies = migrationService.generateMovies();

        List<Movie> expectedMoviesByCategory = testMovies.stream()
                .sorted(Comparator.comparing(Movie::getCategory))
                .collect(Collectors.toList());

        List<MovieDto> expectedMovieDtosByCategory = new ArrayList<>();
        expectedMoviesByCategory.forEach(movie -> expectedMovieDtosByCategory.add(MovieConverter.getMovieDto(movie)));

        when(movieRepositoryMock.findAllOrderedBy(new Sort(Sort.Direction.ASC, "category")))
                .thenReturn(expectedMoviesByCategory);

        assertEquals(expectedMovieDtosByCategory, movieService.getAllMoviesByCategory());

        verify(movieRepositoryMock,times(1))
                .findAllOrderedBy(new Sort(Sort.Direction.ASC, "category"));

    }

    @Test
    public void getAllMoviesWithRateGraterThenTest() {

        double rateValue = 7.1;

        List<Movie> testMovies = migrationService.generateMovies();

        List<Movie> expectedMovies = testMovies.stream()
                .filter(movie -> movie.getRate().getRateValue() > rateValue)
                .collect(Collectors.toList());

        when(movieRepositoryMock.findByRateRateValueGreaterThanQuery(7.1)).thenReturn(expectedMovies);

        List<MovieDto> expectedMovieDtos = new ArrayList<>();
        expectedMovies.forEach(movie -> expectedMovieDtos.add(MovieConverter.getMovieDto(movie)));

        assertEquals(expectedMovieDtos, movieService.getAllMoviesWithRateGraterThen(rateValue));

        verify(movieRepositoryMock, times(1)).findByRateRateValueGreaterThanQuery(rateValue);
    }

    @Test
    public void getAllMoviesByEnumCategoryTest() {

        List<Movie> testMovies = migrationService.generateMovies();

        Category testCategory = Category.ACT;

        List<Movie> expectedMovies = testMovies.stream()
                .filter(movie -> movie.getCategory().equals(testCategory))
                .collect(Collectors.toList());

        when(movieRepositoryMock.findAllByCategory(testCategory)).thenReturn(expectedMovies);

        assertEquals(expectedMovies, movieService.getAllMoviesByEnumCategory(testCategory));

        verify(movieRepositoryMock, times(1)).findAllByCategory(testCategory);
    }
}
