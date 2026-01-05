package org.example.aop.service;

import org.example.aop.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    private final ArrayList<Book> books =
            new ArrayList<>(Arrays.asList(
                    new Book("Dune", 412),
                    new Book("Clean Code", 464),
                    new Book("The Hobbit", 310)
            ));

    public Book getByTitle(String title) {
        System.out.println("getByTitle called");
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                return b;
            }
        }
        return null;
    }

    public List<Book> getAll() {
        System.out.println("getAll called");
        return books;
    }

    public Book create(String title, int pages) {
        System.out.println("create called");
        Book created = new Book(title, pages);
        books.add(created);
        return created;
    }
}