package com.smile.blog.services;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.repositories.AuthorRepository;
import com.smile.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

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
        Author author = authors.get();
        return author;
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

   /* public static Author findAuthorByNikname(Author author){
        //ищем автора и если его нет, то создаем
        Iterable<Author> authors = authorRepository.findAll();
        ArrayList<Author> res = new ArrayList<>();
        authors.forEach(r -> res.add(r));
        res.stream().filter(r -> r.getNickname()==author.getNickname());
        author = res.get(0);
        return author;
    }*/
}
