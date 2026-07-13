package com.axsos.gameM.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axsos.gameM.models.Game;
import com.axsos.gameM.repositories.GameRepository;

@Service
public class GameService {

    private final GameRepository gameRepository;

    // Dependency Injection
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Get all games
    public List<Game> allGames() {
        return gameRepository.findAll();
    }

    // Create or update game
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    // Find one game by id
    public Game findGame(Long id) {
        Optional<Game> optionalGame = gameRepository.findById(id);
        return optionalGame.orElse(null);
    }

    // Delete game
    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }

    // Sort games depending on clicked column
    public List<Game> sortedGames(String sort) {

        if (sort == null) {
            return gameRepository.findAll();
        }

        if (sort.equals("game")) {
            return gameRepository.findAllByOrderByGameNameAsc();
        }

        if (sort.equals("genre")) {
            return gameRepository.findAllByOrderByGenreAsc();
        }

        if (sort.equals("date")) {
            return gameRepository.findAllByOrderByReleaseDateAsc();
        }

        return gameRepository.findAll();
    }
}