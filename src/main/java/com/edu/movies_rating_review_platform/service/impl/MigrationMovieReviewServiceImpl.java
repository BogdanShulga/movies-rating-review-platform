package com.edu.movies_rating_review_platform.service.impl;

import com.edu.movies_rating_review_platform.entity.Movie;
import com.edu.movies_rating_review_platform.entity.Rate;
import com.edu.movies_rating_review_platform.entity.Review;
import com.edu.movies_rating_review_platform.enums.Category;
import com.edu.movies_rating_review_platform.repository.MovieRepository;
import com.edu.movies_rating_review_platform.service.MigrationMovieReviewService;
import com.edu.movies_rating_review_platform.service.MovieService;
import com.edu.movies_rating_review_platform.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MigrationMovieReviewServiceImpl implements MigrationMovieReviewService {

    private final ReviewService reviewService;

    private final MovieRepository movieRepository;

    public ResponseEntity<String> migrate() {

        List<Movie> movies = generateMovies();

        for (Movie movie: movies) {
            movieRepository.save(movie);
        }

        List<Review> reviews = generateReview();

        for (Review review: reviews) {
            reviewService.addReview(review);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Added 5 documents to Movies collection and 10 documents reviews to Reviews collection!");
    }

    private List<Movie> generateMovies() {
        List<Movie> movies = new ArrayList<>();

        Movie movie1 = new Movie();
        movie1.setId(1L);
        movie1.setName("Spider-Man: Far from Home");
        movie1.setCategory(Category.ACT);
        movie1.setDirector("Jon Watts");
        movie1.setShortDescription("In this adventure, Spider-man faces new challenges after Avengers: Endgame.");
        
        Rate rate1 = new Rate();
        rate1.setRateValue(7.9);
        rate1.setVoteCount(532);
        
        movie1.setRate(rate1);

        Movie movie2 = new Movie();
        movie2.setId(2L);
        movie2.setName("John Wick: Chapter 3 - Parabellum");
        movie2.setCategory(Category.THR);
        movie2.setDirector("Chad Stahelski");
        movie2.setShortDescription("It seems that it will return to a very dangerous and horrifying turn when the " +
                "skillful killer John Wick (Keanu Reeves) comes back to offer a new set of dominant killing.");

        Rate rate2 = new Rate();
        rate2.setRateValue(7.9);
        rate2.setVoteCount(738);

        movie2.setRate(rate2);

        Movie movie3 = new Movie();
        movie3.setId(3L);
        movie3.setName("Aladdin");
        movie3.setCategory(Category.ADV);
        movie3.setDirector("Guy Ritchie");
        movie3.setShortDescription("American Adventure and comedy movie directed by Jay Ritchie and produced by " +
                "Walt Disney Pictures.");

        Rate rate3 = new Rate();
        rate3.setRateValue(7.4);
        rate3.setVoteCount(1147);

        movie3.setRate(rate3);

        Movie movie4 = new Movie();
        movie4.setId(4L);
        movie4.setName("Godzilla: King of the Monsters");
        movie4.setCategory(Category.FAN);
        movie4.setDirector("Jon Watts");
        movie4.setShortDescription("In this adventure, Spider-man faces new challenges after Avengers: Endgame.");

        Rate rate4 = new Rate();
        rate4.setRateValue(6.4);
        rate4.setVoteCount(345);

        movie4.setRate(rate4);

        Movie movie5 = new Movie();
        movie5.setId(5L);
        movie5.setName("The Lion King");
        movie5.setCategory(Category.FAM);
        movie5.setDirector("Jon Favreau");
        movie5.setShortDescription("In an energizing and sensational atmosphere, this film pursues the story of " +
                "Simba, a youthful lion whose father was killed leaving him with few companions, " +
                "numerous adversaries and haters.");

        Rate rate5 = new Rate();
        rate5.setRateValue(7.1);
        rate5.setVoteCount(1287);

        movie5.setRate(rate5);

        movies.add(movie1);
        movies.add(movie2);
        movies.add(movie3);
        movies.add(movie4);
        movies.add(movie5);

        return movies;
    }

    private List<Review> generateReview() {
        List<Review> reviews = new ArrayList<>();

        Review review1 = new Review();
        review1.setId(1L);
        review1.setMovieId(1L);
        review1.setLiked(true);
        review1.setReviewMessage("kinda disappointed the HD version isn't on this website, please add it as " +
                "soon as u can");

        Review review2 = new Review();
        review2.setId(2L);
        review2.setMovieId(1L);
        review2.setLiked(true);
        review2.setReviewMessage("And the opening where Nick Fury and Maria Hill went to Mexico, and meet Mysterio? " +
                "Where is that part? Same thing with the sequence where Spiderman is exposed; and Nick Fury aboard " +
                "the Skrull space station scene.");

        Review review3 = new Review();
        review3.setId(3L);
        review3.setMovieId(2L);
        review3.setLiked(true);
        review3.setReviewMessage("Winston: what do you need? John: Guns, lots of guns. Am I the only one " +
                "to actually laugh out loud?");

        Review review4 = new Review();
        review4.setId(4L);
        review4.setMovieId(2L);
        review4.setLiked(true);
        review4.setReviewMessage("Thank u for the service I was dying to watch this.. And couldn't go to " +
                "theater thanks my sickness");

        Review review5 = new Review();
        review5.setId(5L);
        review5.setMovieId(3L);
        review5.setLiked(true);
        review5.setReviewMessage("cool movie so coooooooooooooooooooooooooooooooooooooooooooL make another " +
                "episode pls");

        Review review6 = new Review();
        review6.setId(6L);
        review6.setMovieId(3L);
        review6.setLiked(true);
        review6.setReviewMessage("love it this is the best link I can find the movies for my self thang you " +
                "movies123.pro");

        Review review7 = new Review();
        review7.setId(7L);
        review7.setMovieId(4L);
        review7.setLiked(true);
        review7.setReviewMessage("Yo! Billie brown is in this movie she's in stranger things to awesome!!!!!!!");

        Review review8 = new Review();
        review8.setId(8L);
        review8.setMovieId(4L);
        review8.setLiked(true);
        review8.setReviewMessage("Before:YES IT CAME OUT After realizing it's quality is cam: NOOOOOOOOOOOOOOO");

        Review review9 = new Review();
        review9.setId(9L);
        review9.setMovieId(5L);
        review9.setLiked(true);
        review9.setReviewMessage("UwU");

        Review review10 = new Review();
        review10.setId(10L);
        review10.setMovieId(5L);
        review10.setLiked(true);
        review10.setReviewMessage("wow!!!!!");

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);
        reviews.add(review6);
        reviews.add(review7);
        reviews.add(review8);
        reviews.add(review9);
        reviews.add(review10);

        return reviews;
    }
}
