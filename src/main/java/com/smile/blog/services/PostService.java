package com.smile.blog.services;

import com.smile.blog.models.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface PostService {

    Iterable<Post> getAllPost();

    void postAdd(Post post);

    ArrayList<Post> postDetails(Long id);

    void findPostById(Long id);

    void blogPostUpdate(String subjectPost,  String anonsPost,  String fullTextPost, String tag);
    void postRemoveById(Long id);

    void saveAndResetPost();

    Post getPost();
}
