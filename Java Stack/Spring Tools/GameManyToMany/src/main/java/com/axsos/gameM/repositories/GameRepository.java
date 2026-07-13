package com.axsos.gameM.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.axsos.gameM.models.Game;

public interface GameRepository extends CrudRepository<Game, Long> {
    // Get all games
    List<Game> findAll();

    // Sorting by game name
    List<Game> findAllByOrderByGameNameAsc();

    // Sorting by genre
    List<Game> findAllByOrderByGenreAsc();

    // Sorting by release date
    List<Game> findAllByOrderByReleaseDateAsc();

}
