package com.axsos.book.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.axsos.book.models.Book;
import com.axsos.book.models.User;
import com.axsos.book.services.BookService;
import com.axsos.book.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class BookController {

    private final BookService bookService;
    private final UserService userService;

    public BookController(
            BookService bookService,
            UserService userService) {

        this.bookService = bookService;
        this.userService = userService;
    }

    /*
     * Helper method for getting the logged-in user's ID.
     */
    private Long currentUserId(HttpSession session) {
        return (Long) session.getAttribute("userId");
    }

    /*
     * Display all books.
     */
    @GetMapping("/books")
    public String books(Model model, HttpSession session) {

        Long userId = currentUserId(session);

        if (userId == null) {
            return "redirect:/";
        }

        User loggedUser = userService.findUser(userId);

        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("books", bookService.allBooks());

        return "books.jsp";
    }

    /*
     * Display the create-book form.
     */
    @GetMapping("/books/new")
    public String newBook(
            @ModelAttribute("book") Book book,
            HttpSession session) {

        if (currentUserId(session) == null) {
            return "redirect:/";
        }

        return "newBook.jsp";
    }

    /*
     * Create a book and associate it with the logged-in user.
     */
    @PostMapping("/books")
    public String createBook(
            @Valid @ModelAttribute("book") Book book,
            BindingResult result,
            HttpSession session) {

        Long userId = currentUserId(session);

        if (userId == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            return "newBook.jsp";
        }

        User loggedUser = userService.findUser(userId);

        book.setPostedBy(loggedUser);

        bookService.createBook(book);

        return "redirect:/books";
    }

    /*
     * Display one book.
     */
    @GetMapping("/books/{id}")
    public String showBook(
            @PathVariable("id") Long id,
            Model model,
            HttpSession session) {

        if (currentUserId(session) == null) {
            return "redirect:/";
        }

        Book book = bookService.findBook(id);

        if (book == null) {
            return "redirect:/books";
        }

        model.addAttribute("book", book);
        model.addAttribute("userId", currentUserId(session));

        return "showBook.jsp";
    }

    /*
     * Display the edit form.
     *
     * Only the user who created the book can access it.
     */
    @GetMapping("/books/{id}/edit")
    public String editBook(
            @PathVariable("id") Long id,
            Model model,
            HttpSession session) {

        Long userId = currentUserId(session);

        if (userId == null) {
            return "redirect:/";
        }

        Book book = bookService.findBook(id);

        if (book == null) {
            return "redirect:/books";
        }

        if (!book.getPostedBy().getId().equals(userId)) {
            return "redirect:/books";
        }

        model.addAttribute("book", book);

        return "editBook.jsp";
    }

    /*
     * Update a book.
     *
     * We load the existing book first to protect its owner.
     */
    @PutMapping("/books/{id}")
    public String updateBook(
            @PathVariable("id") Long id,
            @Valid @ModelAttribute("book") Book formBook,
            BindingResult result,
            HttpSession session) {

        Long userId = currentUserId(session);

        if (userId == null) {
            return "redirect:/";
        }

        Book existingBook = bookService.findBook(id);

        if (existingBook == null) {
            return "redirect:/books";
        }

        if (!existingBook.getPostedBy().getId().equals(userId)) {
            return "redirect:/books";
        }

        if (result.hasErrors()) {
            return "editBook.jsp";
        }

        /*
         * Update only the editable fields.
         *
         * Do not replace postedBy because the original user must remain
         * the owner of the book.
         */
        existingBook.setTitle(formBook.getTitle());
        existingBook.setAuthor(formBook.getAuthor());
        existingBook.setDescription(formBook.getDescription());

        bookService.updateBook(existingBook);

        return "redirect:/books/" + id;
    }

    /*
     * Delete a book.
     *
     * Only the user who created the book can delete it.
     */
    @DeleteMapping("/books/{id}")
    public String deleteBook(
            @PathVariable("id") Long id,
            HttpSession session) {

        Long userId = currentUserId(session);

        if (userId == null) {
            return "redirect:/";
        }

        Book book = bookService.findBook(id);

        if (book == null) {
            return "redirect:/books";
        }

        if (!book.getPostedBy().getId().equals(userId)) {
            return "redirect:/books";
        }

        bookService.deleteBook(id);

        return "redirect:/books";
    }
}
