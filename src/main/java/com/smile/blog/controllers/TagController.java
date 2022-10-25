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

  /*  @PostMapping("/blog/addPost/tag")
    public  String blogAddTagPost(@RequestParam String tag, @RequestParam String subjectPost, @RequestParam String anonsPost, @RequestParam String fullTextPost){
        BlogController.post.setSubjectPost(subjectPost);
        BlogController.post.setAnonsPost(anonsPost);
        BlogController.post.setFullTextPost(fullTextPost);
        Tag tagNew = tagService.findTagByName(tag);
        if (tagNew!=null)       tags.add(tagNew);
        return  "redirect:/blog/addPost";
    }
*/
    @GetMapping("/blog/addTag/{name}/{shortDescription}")
    public String blogAddTagPost(@PathVariable(value = "name") String name,@PathVariable(value = "shortDescription") String shortDescription){
       tagService.addTag(name,shortDescription);
        System.out.println(name);
        return "redirect:/blog";
    }
}
