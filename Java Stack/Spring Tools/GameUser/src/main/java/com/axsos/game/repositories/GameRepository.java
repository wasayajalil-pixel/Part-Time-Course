package com.axsos.game.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.game.models.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
//     Get all games.

	List<Game> findAll();

	/*
	 * Sort games by name (A-Z)
	 */

	List<Game> findAllByOrderByNameAsc();

	/*
	 * Sort games by genre (A-Z)
	 */

	List<Game> findAllByOrderByGenreAsc();

	/*
	 * Sort games by release date (Oldest → Newest)
	 */

	List<Game> findAllByOrderByReleaseDateAsc();
	
// sort games by name (Z-A)
	
	List<Game> findAllByOrderByNameDesc();
}
