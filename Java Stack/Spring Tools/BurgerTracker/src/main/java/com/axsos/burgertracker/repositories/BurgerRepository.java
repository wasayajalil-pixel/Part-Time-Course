package com.axsos.burgertracker.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.burgertracker.models.Burger;
@Repository
public interface BurgerRepository extends CrudRepository<Burger, Long>{
    // Get all burgers from database
    List<Burger> findAll();
}
