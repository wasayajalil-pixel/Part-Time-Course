package com.axsos.project.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axsos.project.models.Project;
import com.axsos.project.repositories.ProjectRepository;

@Service
public class ProjectService {
	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> allProjects() {
		return projectRepository.findAllByOrderByCreatedAtDesc();
	}

	public Project createProject(Project project) {
		return projectRepository.save(project);
	}

	public Project findProject(Long id) {
		return projectRepository.findById(id).orElse(null);
	}

	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}

	public void deleteProject(Long id) {
		projectRepository.deleteById(id);
	}

	// Sort games
	public List<Project> sortProjects(String sortBy) {

	        if (sortBy == null) {
	            return projectRepository.findAllByOrderByCreatedAtDesc();
	        }

	        switch (sortBy) {

	            case "title":
	                return projectRepository.findAllByOrderByTitleAsc();

	            case "technology":
	                return projectRepository.findAllByOrderByTechnologyAsc();

	            case "releaseDate":
	                return projectRepository.findAllByOrderByReleaseDateAsc();

	            default:
	                return projectRepository.findAllByOrderByCreatedAtDesc();
	        }
	}
	

}
