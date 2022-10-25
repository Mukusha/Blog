package com.smile.blog.controllers;

import com.smile.blog.models.Tag;
import com.smile.blog.services.PostService;
import com.smile.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

@Controller
public class TagController {
    public static Set<Tag> tags = new HashSet<>();

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @GetMapping("/blog/addTag/{name}/{shortDescription}")
    public String blogAddTagPost(@PathVariable(value = "name") String name,@PathVariable(value = "shortDescription") String shortDescription){
       tagService.addTag(name,shortDescription);
        System.out.println(name);
        return "redirect:/blog";
    }

    @GetMapping("/blog/addPost/removeTag/{idTag}")
    public String deleteTagInPostById(@PathVariable(value = "idTag") long id){
        tags.remove(TagService.findTagById(id));
        return "redirect:/blog/addPost/tag";
    }
}
