package com.smile.blog.repositories;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post,Long> {
    List<Post>  findAllByOrderByIdDesc();
    List<Post> findByAuthor(Author author);

    List<Post> findByTags(Tag tag);

    List<Post> findBySubjectPostContainingIgnoreCaseAndAnonsPostContainingIgnoreCaseAndFullTextPostContainingIgnoreCaseAndAuthorNicknameContainingIgnoreCase(String SubjectPost, String AnonsPost,String FullTextPost,String author);

    List<Post> findBySubjectPostContainingIgnoreCaseOrAnonsPostContainingIgnoreCaseOrFullTextPostContainingIgnoreCaseOrAuthorNicknameContainingIgnoreCase(String SubjectPost, String AnonsPost,String FullTextPost, String author);

}
