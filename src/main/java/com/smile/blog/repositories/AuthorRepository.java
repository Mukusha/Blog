package com.smile.blog.repositories;

import com.smile.blog.models.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Long> {
    List<Author> findByNicknameContainingIgnoreCaseAndShortInformationContainingIgnoreCase(String Nickname,String ShortInformation);
}
