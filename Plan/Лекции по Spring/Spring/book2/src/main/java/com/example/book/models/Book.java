package com.example.book.models;

import javax.persistence.*;

@Entity
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id ;
    private  String author;
    private  String name;

    public Book(String author, String name) {
        this.author = author;
        this.name = name;
    }
    public Book() {}

    public Long getId() { return id; }

    public String getAuthor() { return author; }

    public String getName() { return name; }
}
