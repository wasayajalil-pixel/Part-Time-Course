package com.axsos.gameM.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axsos.gameM.models.Game;
import com.axsos.gameM.models.Rating;
import com.axsos.gameM.models.User;
import com.axsos.gameM.repositories.RatingRepository;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    // Dependency Injection
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    // Add rate or update old rate
    public void rateGame(User user, Game game, int rate) {

        Optional<Rating> oldRating = ratingRepository.findByUserAndGame(user, game);

        if (oldRating.isPresent()) {
            Rating rating = oldRating.get();
            rating.setRate(rate);
            ratingRepository.save(rating);
        } else {
            Rating rating = new Rating(rate, user, game);
            ratingRepository.save(rating);
        }
    }
}