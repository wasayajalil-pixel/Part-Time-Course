package com.axsos.burgertracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.axsos.burgertracker.models.Burger;

public interface BurgerRepository extends CrudRepository<Burger, Long>{
    // Get all burgers from database
    List<Burger> findAll();
}
