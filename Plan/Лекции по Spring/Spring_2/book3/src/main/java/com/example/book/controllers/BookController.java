package com.example.book.controllers;

import com.example.book.models.Book;
import com.example.book.servis.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public  BookController(BookService bookService) {this.bookService = bookService;}

    @GetMapping(value = "/books")
    @ResponseBody
    public List<Book> getAllBooks()
    {
       return bookService.getAllBooks();
    }

    @GetMapping(value="/save/{name}/{author}")
    @ResponseBody
    public Book save(@PathVariable String name, @PathVariable String author)
    {
            return bookService.saveBook(name, author);
}
}
