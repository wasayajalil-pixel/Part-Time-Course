package com.axsos.gameM.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.axsos.gameM.models.Game;
import com.axsos.gameM.models.Rating;
import com.axsos.gameM.models.User;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    // Find all ratings for one game
    List<Rating> findByGameOrderByRateDesc(Game game);

    // Check if this user already rated this game
    Optional<Rating> findByUserAndGame(User user, Game game);
}
