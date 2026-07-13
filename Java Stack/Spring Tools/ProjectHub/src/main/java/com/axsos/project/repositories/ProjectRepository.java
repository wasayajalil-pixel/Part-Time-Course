package com.axsos.project.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.project.models.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
	List<Project> findAllByOrderByCreatedAtDesc();
	
    // Title from A to Z
    List<Project> findAllByOrderByTitleAsc();
    
    List<Project> findAllByOrderByTechnologyAsc();

    // Old release date to new release date
    List<Project> findAllByOrderByReleaseDateAsc();

    // New release date to old release date
    List<Project> findAllByOrderByReleaseDateDesc();

}
