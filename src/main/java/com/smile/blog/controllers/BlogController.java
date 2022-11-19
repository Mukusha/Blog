package com.smile.blog.controllers;

import com.smile.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/blog")
    public  String blogMain(Model model){

        model.addAttribute("posts",postService.getAllPost());
        return "home";
    }
}
