package com.smile.blog.services;

import com.smile.blog.models.Author;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface AuthorService {

    void profileAdd(String fioAuthor, String nickname, String shortInformation);

    Author findAuthorById(Long id);

   Long findIdByNickname(String nickname);
}
