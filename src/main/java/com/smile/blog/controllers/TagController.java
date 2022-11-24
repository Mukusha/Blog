package com.smile.blog.controllers;

import com.smile.blog.services.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/blog/addTag/{name}/{shortDescription}")
    public String blogAddTagPost(@PathVariable(value = "name") String name, @PathVariable(value = "shortDescription") String shortDescription){
        tagService.addTag(name,shortDescription);
        return "redirect:/blog";
    }

    @GetMapping("/blog/tag/{id}")
    public String tagInfo(@PathVariable(value = "id") Long id, Model model){
        model.addAttribute("tag",tagService.findTagById(id));
        model.addAttribute("posts",tagService.postFindByTagId(id) );
        return "tagDetails";
    }
}
