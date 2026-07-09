package com.axsos.game.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axsos.game.models.Game;
import com.axsos.game.repositories.GameRepository;

@Service
public class GameService {
	private final GameRepository gameRepository;

	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	// Get all games
	public List<Game> allGames() {
		return gameRepository.findAll();
	}

	// Sort games
	public List<Game> sortGames(String sortBy) {

		if (sortBy == null) {
			return gameRepository.findAll();
		}

		if (sortBy.equals("name")) {
			return gameRepository.findAllByOrderByNameAsc();
		}

		if (sortBy.equals("genre")) {
			return gameRepository.findAllByOrderByGenreAsc();
		}

		if (sortBy.equals("releaseDate")) {
			return gameRepository.findAllByOrderByReleaseDateAsc();
		}

		return gameRepository.findAll();
	}

	// Create or update game
	public Game saveGame(Game game) {
		return gameRepository.save(game);
	}

	// Find one game
	public Game findGame(Long id) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		if (optionalGame.isPresent()) {
			return optionalGame.get();
		}

		return null;
	}

	// Delete game
	public void deleteGame(Long id) {
		gameRepository.deleteById(id);
	}

}
