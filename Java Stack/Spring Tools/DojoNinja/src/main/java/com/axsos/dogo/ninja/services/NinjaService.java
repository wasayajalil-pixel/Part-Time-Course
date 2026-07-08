package com.axsos.dogo.ninja.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.axsos.dogo.ninja.models.Ninja;
import com.axsos.dogo.ninja.repositories.NinjaRepository;

@Service
public class NinjaService {
	private final NinjaRepository ninjaRepository;

	public NinjaService(NinjaRepository ninjaRepository) {
		this.ninjaRepository = ninjaRepository;
	}
	//get all the ninja
	public List<Ninja> allNinjas(){
		return (List<Ninja>) ninjaRepository.findAll();
	}
	//create or update the ninja
	public Ninja createNinja(Ninja ninja) {
		return ninjaRepository.save(ninja);
	}

}
