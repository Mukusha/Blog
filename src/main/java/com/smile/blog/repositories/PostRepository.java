package com.smile.blog.repositories;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post,Long> {
    Iterable<Post> findByAuthor(Author author);
}
