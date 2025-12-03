package org.example.testproject;

import com.example.api.BooksApi;
import com.example.models.Book;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController implements BooksApi {
    @Override
    public ResponseEntity<Void> createBook(@Valid Book body) {
        System.out.println(body.getName());
        return null;
    }

    @Override
    public ResponseEntity<List<Book>> getAllBooksInLibrary() {
        List<Book> books = new ArrayList<>();
        books.add(new Book());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Book> getBookById(String bookId){
        Book book = new Book();
        book.setAuthor(bookId);
        book.setName(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
