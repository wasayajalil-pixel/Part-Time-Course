package com.axsos.booksapi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.booksapi.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {

}
