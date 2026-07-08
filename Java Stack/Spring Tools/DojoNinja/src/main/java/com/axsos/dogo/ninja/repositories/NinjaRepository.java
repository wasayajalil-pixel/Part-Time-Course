package com.axsos.dogo.ninja.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.axsos.dogo.ninja.models.Ninja;
@Repository
public interface NinjaRepository extends CrudRepository<Ninja, Long>{

}
