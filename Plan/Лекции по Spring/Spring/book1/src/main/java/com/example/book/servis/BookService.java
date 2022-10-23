package com.example.book.servis;

import com.example.book.models.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookService {
    List<Book> books = new ArrayList<>();
    public Book saveBook( String name, String author)
    {
        Book book = new Book(name,  author);
        books.add(book);
        return book;
    }

    public List<Book> getAllBooks()
    {
        return books;
    }

    public void add(){
        books.add(new Book("Дубровский", "А.С. Пушкин"));
        books.add(new Book("Преступление и наказание", "Ф.М. Достоевский"));
    }
}
