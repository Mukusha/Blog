package com.smile.blog.services;

import com.smile.blog.models.Post;
import com.smile.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PostService {
    private static PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post saveBook(String name, String author)
    {
     /*   Post post = new Post(name,  author);
        postRepository.save(post);
        return post;*/
        return null;
    }

    public  Iterable<Post> getAllPost()
    {
        Iterable<Post> posts = postRepository.findAll();
       // List<Post> posts = new ArrayList<>();
      //  postRepository.findAll().forEach(posts::add);
        return posts;
    }

    public void postAdd(){
     /*  Post post=new Post(title,anons,full_text);
       postRepository.save(post);*/
    }

    public void postDetails(){
    /*    if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post",res);*/
    }

    public ArrayList<Post> findPostById(){
     /*   if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }
        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        return res;*/
        return null;
    }

    public void postSaveEdit(){
     /*   Post post=postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);
        postRepository.save(post);*/
    }

    public void postRemoveById(Long id){
        Post post=postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
    }
    /*
    public void add(){
        books.add(new Book("Дубровский", "А.С. Пушкин"));
        books.add(new Book("Преступление и наказание", "Ф.М. Достоевский"));
    }*/
}
