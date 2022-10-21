package com.smile.blog.controllers;

import com.smile.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String blogPostAdd(@RequestParam String title, @RequestParam String anons, @RequestParam String full_text, Model model){
        postService.postAdd();
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    public  String blogDetails(@PathVariable(value = "id") long id, Model model){
        //открыть подробности определенного поста
        postService.postDetails();
        return "postDetails";
    }

    @GetMapping("/blog/{id}/edit")
    public  String blogEdit(@PathVariable(value = "id") long id, Model model){
        model.addAttribute("post",postService.findPostById());
        return "postEdit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id,@RequestParam String title, @RequestParam String anons,@RequestParam String full_text,Model model){
        postService.postSaveEdit();
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id,Model model){
        postService.postRemoveById(id);
        return "redirect:/blog";
    }
}
