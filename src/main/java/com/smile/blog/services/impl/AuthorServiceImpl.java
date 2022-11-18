package com.smile.blog.services.impl;

import com.smile.blog.models.Author;
import com.smile.blog.repositories.AuthorRepository;
import com.smile.blog.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private static AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author profileAdd(String nickname){
        //позже добавить сохранение даты
        Author author = new Author( nickname);
        authorRepository.save(author);
        return author;
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

    @Override
    public void AuthorSaveUpdate(Long id,String nickname, String shortInformation, String dateOfBirth) throws ParseException {
        Author author=authorRepository.findById(id).orElseThrow();
        author.setNickname(nickname);
        author.setShortInformation(shortInformation);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = dateFormat.parse(dateOfBirth);
        Timestamp t = new Timestamp( parsedDate.getTime());
        author.setDateOfBirth(t);
        authorRepository.save(author);
    }
}
