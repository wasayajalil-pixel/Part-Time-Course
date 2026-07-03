package com.axsos.booksapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axsos.booksapi.models.Book;
import com.axsos.booksapi.repositories.BookRepository;
@Service
public class BookService {
	// Connect to Repository
    private final BookRepository bookRepository;
    
    // Constructor Injection
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    // Return all books
    public List<Book> allBooks() {
        return (List<Book>) bookRepository.findAll();
    }
    // Save a new book
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    // Find one book using its id
    public Book findBook(Long id) {

        Optional<Book> optionalBook = bookRepository.findById(id);

        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    // Update an existing book
    public Book updateBook(Long id,String title,String desc,String lang,Integer pages) {

        Book book = findBook(id);

        book.setTitle(title);
        book.setDescription(desc);
        book.setLanguage(lang);
        book.setNumberOfPages(pages);

        return bookRepository.save(book);
    }
    // Delete a book
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    
}
