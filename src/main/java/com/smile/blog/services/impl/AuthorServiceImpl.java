package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.repositories.AuthorRepository;
import com.smile.blog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void profileAdd(String fioAuthor, String nickname, String shortInformation){
        //позже добавить сохранение даты
        Author author = new Author(fioAuthor,  nickname,  shortInformation);
        authorRepository.save(author);
    }
    @Override
    public Author findAuthorById(Long id){
        if(!authorRepository.existsById(id)){
            return null;
        }
        Optional<Author> authors = authorRepository.findById(id);
        return authors.get();
    }

    @Override
    public Long findIdByNickname(String nickname){
        Long id=0l;

        if(!authorRepository.existsById(id)){
            return null;
        }
        Optional<Author> authors = authorRepository.findById(id);
        Author author = authors.get();
        return id;
    }
}
