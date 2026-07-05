package com.axsos.booksapi.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.axsos.booksapi.models.Book;
import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
  List<Book> findAll();
  Optional<Book> findById(Long id);

}
