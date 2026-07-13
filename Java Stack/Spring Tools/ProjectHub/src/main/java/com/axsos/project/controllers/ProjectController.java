package com.axsos.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.axsos.project.models.Project;
import com.axsos.project.services.ProjectService;
import com.axsos.project.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ProjectController {

	private final ProjectService projectService;
	private final UserService userService;

	// Dependency Injection
	public ProjectController(ProjectService projectService, UserService userService) {
		this.projectService = projectService;
		this.userService = userService;
	}

	// Get the logged-in user ID from the session
	private Long currentUserId(HttpSession session) {
		return (Long) session.getAttribute("userId");
	}

	// Show all projects
	@GetMapping("/projects")
	public String dashboard(
			@RequestParam(value = "sort", required = false) String sort,
			Model model,
			HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		model.addAttribute("loggedUser", userService.findUser(userId));

		if (sort != null) {
			model.addAttribute("projects", projectService.sortProjects(sort));
		} else {
			model.addAttribute("projects", projectService.allProjects());
		}

		model.addAttribute("newProject", new Project());

		return "dashboard.jsp";
	}

	// Create a new project
	@PostMapping("/projects")
	public String create(
			@Valid @ModelAttribute("newProject") Project project,
			BindingResult result,
			Model model,
			HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		if (result.hasErrors()) {
			model.addAttribute("loggedUser", userService.findUser(userId));
			model.addAttribute("projects", projectService.allProjects());

			return "dashboard.jsp";
		}

		project.setCreator(userService.findUser(userId));
		projectService.createProject(project);

		return "redirect:/projects";
	}

	// Show project details
	@GetMapping("/projects/{id}")
	public String details(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		Project project = projectService.findProject(id);

		if (project == null) {
			return "redirect:/projects";
		}

		model.addAttribute("project", project);
		model.addAttribute("loggedUserId", userId);

		return "details.jsp";
	}

	// Show edit project page
	@GetMapping("/projects/{id}/edit")
	public String editPage(
			@PathVariable("id") Long id,
			Model model,
			HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		Project project = projectService.findProject(id);

		if (project == null ||
				!project.getCreator().getId().equals(userId)) {

			return "redirect:/projects";
		}

		model.addAttribute("project", project);

		return "edit.jsp";
	}

	// Update an existing project
	@PostMapping("/projects/{id}")
	public String update(
			@PathVariable("id") Long id,
			@Valid @ModelAttribute("project") Project formProject,
			BindingResult result,
			HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		Project existingProject = projectService.findProject(id);

		if (existingProject == null ||
				!existingProject.getCreator().getId().equals(userId)) {

			return "redirect:/projects";
		}

		if (result.hasErrors()) {
			return "edit.jsp";
		}

		existingProject.setTitle(formProject.getTitle());
		existingProject.setDescription(formProject.getDescription());
		existingProject.setTechnology(formProject.getTechnology());
		existingProject.setReleaseDate(formProject.getReleaseDate());

		projectService.updateProject(existingProject);

		return "redirect:/projects/" + id;
	}

	// Delete a project
	@DeleteMapping("/projects/{id}")
	public String delete(
			@PathVariable("id") Long id,
			HttpSession session) {

		Long userId = currentUserId(session);

		if (userId == null) {
			return "redirect:/";
		}

		Project project = projectService.findProject(id);

		if (project != null &&
				project.getCreator().getId().equals(userId)) {

			projectService.deleteProject(id);
		}

		return "redirect:/projects";
	}
}
