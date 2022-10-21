package com.smile.blog.controllers;

import com.smile.blog.models.Post;
import com.smile.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

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
        return "home";
    }

    @GetMapping("/blog/add")
    public  String blogAdd(Model model){
        return "postAdd";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String subjectPost, @RequestParam String anonsPost, @RequestParam String fullTextPost){
        //пока не указываем автора, он будет указываться в зависимости от профиля пользователя
        // пока не вставляем теги
        postService.postAdd(null,  subjectPost,  anonsPost,  fullTextPost, null);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public  String blogDetails(@PathVariable(value = "id") long id, Model model){
        ArrayList<Post> res= postService.postDetails(id);
        if (res == null) return  "redirect:/blog";
        model.addAttribute("post",res);
        return "postDetails";
    }

    @GetMapping("/blog/{id}/edit")
    public  String blogEdit(@PathVariable(value = "id") long id, Model model){
         ArrayList<Post> res = postService.findPostById(id);
         if(res==null){
            return "redirect:/blog";
         }
        model.addAttribute("post",res);
        return "postEdit";
    }

    @PostMapping("/blog/{id}/edit")
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
