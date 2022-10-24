package com.smile.blog.controllers;

import com.smile.blog.models.Author;
import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.services.PostService;
import com.smile.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class BlogController {

    private final PostService postService;

    @Autowired
    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/blog")
    public  String blogMain(Model model){
        //главная страница со списком постов
        model.addAttribute("posts",postService.getAllPost());
        model.addAttribute("tags", TagService.getAllTag());
        return "home";
    }

    @GetMapping("/blog/addPost")
    public  String blogAdd(Model model){
        if (TagController.tags!=null)        model.addAttribute("tags", TagController.tags.toString());
        return "postAdd";
    }

    @PostMapping("/blog/addPost")
    public String blogPostAdd(@RequestParam String subjectPost, @RequestParam String anonsPost, @RequestParam String fullTextPost, @RequestParam String tag){
        //пока не указываем автора, он будет указываться в зависимости от профиля пользователя
        // пока не вставляем теги
        Author author = new Author("Анохина Алефтина Тимофеевна","Anna","");
       // post = new Post(author,  subjectPost,  anonsPost,  fullTextPost, null);
        postService.postAdd(author,  subjectPost,  anonsPost,  fullTextPost, TagController.tags);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public  String blogDetails(@PathVariable(value = "id") long id, Model model){
        ArrayList<Post> res= postService.postDetails(id);
        if (res == null) return  "redirect:/blog";
        model.addAttribute("post",res);
        return "postDetails";
    }

    @GetMapping("/blog/{id}/editPost")
    public  String blogEdit(@PathVariable(value = "id") long id, Model model){
         ArrayList<Post> res = postService.findPostById(id);
         if(res==null){
            return "redirect:/blog";
         }
        model.addAttribute("post",res);
        return "postEdit";
    }

    @PostMapping("/blog/{id}/editPost")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String subjectPost, @RequestParam String anonsPost, @RequestParam String fullTextPost){
       //пока не указываем автора и теги
        postService.postSaveEdit(id,subjectPost,anonsPost,fullTextPost);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id){
        postService.postRemoveById(id);
        return "redirect:/blog";
    }



}
