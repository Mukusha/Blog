package com.example.book.servis;

import com.example.book.models.Book;
import com.example.book.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {
    private static BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book saveBook( String name, String author)
    {
        Book book = new Book(name,  author);
        bookRepository.save(book);
        return book;
    }

    public List<Book> getAllBooks()
    {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;
    }
    /*
    public void add(){
        books.add(new Book("Дубровский", "А.С. Пушкин"));
        books.add(new Book("Преступление и наказание", "Ф.М. Достоевский"));
    }*/
}
