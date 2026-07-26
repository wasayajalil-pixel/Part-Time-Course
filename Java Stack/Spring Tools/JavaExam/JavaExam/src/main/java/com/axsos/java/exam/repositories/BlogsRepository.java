package com.axsos.java.exam.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.java.exam.models.Blogs;

@Repository
public interface BlogsRepository extends CrudRepository<Blogs, Long> {
	 List<Blogs> findAll();

}
