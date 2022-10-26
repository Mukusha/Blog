package com.smile.blog.services;

import com.smile.blog.controllers.BlogController;
import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Component
public class PostService {
    private static PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public  Iterable<Post> getAllPost()
    {
        return postRepository.findAll();
    }

    public void postAdd(Author author, String subjectPost, String anonsPost, String fullTextPost, Set<Tag> tags){
        //работа должна добавляться только 1 автору - текущему пользователю
        //найти по id и вернуть
        Post post=new Post(AuthorService.findAuthorById(7L),  subjectPost,  anonsPost,  fullTextPost, tags);
        postRepository.save(post);
    }

    public void postAdd(Post post){
        //работа должна добавляться только 1 автору - текущему пользователю
        //найти по id и вернуть
       BlogController.post.setAuthor(AuthorService.findAuthorById(7L));
        System.out.println("postAdd в бд");
        System.out.println(" Tema "+ BlogController.post.getSubjectPost());
        System.out.println(" Anons "+ BlogController.post.getAnonsPost());
        System.out.println(" Full "+ BlogController.post.getFullTextPost());
        postRepository.save( BlogController.post);
    }

    public ArrayList<Post> postDetails(Long id){
        if(!postRepository.existsById(id)){
            return null;
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    public ArrayList<Post> findPostById(Long id){
        if(!postRepository.existsById(id)){
            return null;
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;
    }

    public void postSaveEdit(Long id, String subjectPost, String anonsPost, String fullTextPost, Set<Tag> tags){
        Post post=postRepository.findById(id).orElseThrow();
        // потом добавить автора и теги
        post.setSubjectPost(subjectPost);
        post.setAnonsPost(anonsPost);
        post.setFullTextPost(fullTextPost);
        post.setTags(tags);
        postRepository.save(post);
    }

    public void postRemoveById(Long id){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }
}
