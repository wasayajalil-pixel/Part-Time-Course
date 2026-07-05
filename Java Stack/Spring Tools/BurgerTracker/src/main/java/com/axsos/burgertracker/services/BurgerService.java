package com.axsos.burgertracker.services;

import org.springframework.stereotype.Service;
import com.axsos.burgertracker.models.Burger;
import java.util.List;
import java.util.Optional;

import com.axsos.burgertracker.repositories.BurgerRepository;

@Service
public class BurgerService {
	private final BurgerRepository burgerRepository;

	// Dependency Injection
	public BurgerService(BurgerRepository burgerRepository) {
		this.burgerRepository = burgerRepository;
	}
	//get all burgers
	public List<Burger> allBurgers(){
		return burgerRepository.findAll();
	}
    // Create or update burger
    public Burger saveBurger(Burger burger) {
        return burgerRepository.save(burger);
    }

    // Find one burger by id
    public Burger findBurger(Long id) {
        Optional<Burger> optionalBurger = burgerRepository.findById(id);

        if (optionalBurger.isPresent()) {
            return optionalBurger.get();
        } else {
            return null;
        }
    }

}
