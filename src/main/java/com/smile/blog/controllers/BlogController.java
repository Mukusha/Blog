package com.smile.blog.controllers;

import com.smile.blog.services.FindService;
import com.smile.blog.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogController {
    private final PostService postService;
    private final FindService findService;

    public BlogController(PostService postService, FindService findService) {
        this.postService = postService;
        this.findService = findService;
    }

    @GetMapping("/blog")
    public  String blogMain(Model model){
        model.addAttribute("posts",postService.getAllPost());
        return "home";
    }

    @PostMapping("/blog")
    public  String blogFindPost(@RequestParam String key,Model model){
        //поиск по тексту
        if (!key.equals("")) {  model.addAttribute("posts", findService.findKey(key)); }
        else { model.addAttribute("posts",postService.getAllPost()); }
        return "home";
    }
}
