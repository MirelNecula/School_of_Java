package org.example.aop.controller;

import org.example.aop.model.Book;
import org.example.aop.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@Validated
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        System.out.println("create endpoint called");
        Book saved = bookService.create(book.getTitle(), book.getPages());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Book> getByTitle(@PathVariable String title) {
        System.out.println("getByTitle endpoint called");
        Book found = bookService.getByTitle(title);
        return found != null ? ResponseEntity.ok(found) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        System.out.println("getAll endpoint called");
        return ResponseEntity.ok(bookService.getAll());
    }
}