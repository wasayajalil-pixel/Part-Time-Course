package com.axsos.booksapi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.axsos.booksapi.models.Book;
import com.axsos.booksapi.services.BookService;

@RestController      // Returns JSON instead of JSP pages
public class BooksApi {

    private final BookService bookService;

    public BooksApi(BookService bookService) {
        this.bookService = bookService;
    }

    // Get all books
    @RequestMapping("/api/books")
    public List<Book> index() {
        return bookService.allBooks();
    }

    // Create a new book
    @RequestMapping(value="/api/books",method=RequestMethod.POST)
    public Book create(

            @RequestParam("title") String title,
            @RequestParam("description") String desc,
            @RequestParam("language") String lang,
            @RequestParam("pages") Integer pages){

        Book book = new Book(title,desc,lang,pages);

        return bookService.createBook(book);
    }

    // Get one book by id
    @RequestMapping("/api/books/{id}")
    public Book show(@PathVariable("id") Long id){

        return bookService.findBook(id);
    }

    // Update a book
    @RequestMapping(value="/api/books/{id}",method=RequestMethod.PUT)
    public Book update(

            @PathVariable("id") Long id,
            @RequestParam("title") String title,
            @RequestParam("description") String desc,
            @RequestParam("language") String lang,
            @RequestParam("pages") Integer pages){

        return bookService.updateBook(id,title,desc,lang,pages);
    }

    // Delete a book
    @RequestMapping(value="/api/books/{id}",method=RequestMethod.DELETE)
    public void destroy(@PathVariable("id") Long id){

        bookService.deleteBook(id);
    }
}
