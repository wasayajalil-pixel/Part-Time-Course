package com.axsos.dogo.ninja.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.axsos.dogo.ninja.models.Dojo;
import com.axsos.dogo.ninja.repositories.DojoRepository;

@Service
public class DojoService {
private final DojoRepository dojoRepository;
//Constructor Injection
public DojoService(DojoRepository dojoRepository) {
	this.dojoRepository = dojoRepository;
}
//get all the dojos and display it
public List<Dojo> allDojos() {
    return (List<Dojo>) dojoRepository.findAll();
}
//create and update
public Dojo createDojo(Dojo dojo) {
    return dojoRepository.save(dojo);
}

//if ID exist get the info ,if doesn't exist return null
public Dojo findDojo(Long id) {
    Optional<Dojo> optionalDojo = dojoRepository.findById(id);

    if (optionalDojo.isPresent()) {
        return optionalDojo.get();
    } else {
        return null;
    }
}
// Delete Dojo
public void deleteDojo(Long id) {
	dojoRepository.deleteById(id);
}


}
