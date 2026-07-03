package com.axsos.booksapi.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axsos.booksapi.models.Book;
import com.axsos.booksapi.services.BookService;

@Controller      // Returns JSP pages
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    // Display all books on index.jsp
    @RequestMapping("/books")
    public String index(Model model) {

        // Get all books from database
        List<Book> books = bookService.allBooks();

        // Send books to JSP
        model.addAttribute("books", books);

        // Go to index.jsp
        return "index.jsp";
    }
//    get 1 id and return the result in show page
    @RequestMapping("/books/{id}")
    public String show(@PathVariable("id") Long id, Model model) {

        Book book = bookService.findBook(id);

        model.addAttribute("book", book);

        return "show.jsp";
}
}