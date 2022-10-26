package com.smile.blog.services;

import com.smile.blog.models.Author;
import com.smile.blog.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorService {

    private static AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public static void profileAdd(String fioAuthor, String nickname, String shortInformation){
        //позже добавить сохранение даты
        Author author = new Author(fioAuthor,  nickname,  shortInformation);
        authorRepository.save(author);
    }

    public static Author findAuthorById(Long id){
        if(!authorRepository.existsById(id)){
            return null;
        }
        Optional<Author> authors = authorRepository.findById(id);
        return authors.get();
    }

    public static Long findIdByNickname(String nickname){
        Long id=0l;

        if(!authorRepository.existsById(id)){
            return null;
        }
        Optional<Author> authors = authorRepository.findById(id);
        Author author = authors.get();
        return id;
    }
}
