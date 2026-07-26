package com.axsos.java.exam.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axsos.java.exam.models.Blogs;
import com.axsos.java.exam.repositories.BlogsRepository;

@Service
public class BlogsService {
	private final BlogsRepository blogsRepository;

	public BlogsService(BlogsRepository blogsRepository) {
		this.blogsRepository = blogsRepository;
	}
	
	public List<Blogs> allBlogs(){
		return blogsRepository.findAll();
	}
	
	public Blogs createBlogs(Blogs blogs) {
		return blogsRepository.save(blogs);
	}
	
	// Find one game
	public Blogs findBlogs(Long id) {
		Optional<Blogs> optionalBlogs = blogsRepository.findById(id);
		if (optionalBlogs.isPresent()) {
			return optionalBlogs.get();
		}

		return null;
	}
	
	public Blogs updateBlogs(Blogs blogs) {
		return blogsRepository.save(blogs);
	}
	
	public void deleteBlogs(Long id) {
		blogsRepository.deleteById(id);
		
	}

}
