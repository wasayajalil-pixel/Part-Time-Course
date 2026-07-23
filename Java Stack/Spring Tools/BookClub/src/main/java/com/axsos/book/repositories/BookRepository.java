package com.axsos.book.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.axsos.book.models.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	 List<Book> findAll();

}
