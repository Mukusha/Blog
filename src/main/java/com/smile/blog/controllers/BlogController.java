package com.smile.blog.controllers;

import com.smile.blog.models.Post;
import com.smile.blog.models.User;
import com.smile.blog.services.PostService;
import com.smile.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class BlogController {

    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public BlogController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
    }

    @GetMapping("/blog")
    public  String blogMain(Model model){
        model.addAttribute("posts",postService.getAllPost());
        return "home";
    }

    @GetMapping("/blog/{id}")
    public  String blogDetails(
            @AuthenticationPrincipal User user,
            @PathVariable(value = "id") long id, Model model){
        ArrayList<Post> res= postService.postDetails(id);
        if (res == null) return  "redirect:/blog";
        //имеете ли вы право редактирования
        if(user.getAuthor().getId() != res.get(0).getAuthor().getId())  model.addAttribute("isAuthor",false);
        else model.addAttribute("isAuthor",true);
        model.addAttribute("post",res);
        return "postDetails";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id){
        postService.postRemoveById(id);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}/editPost")
    public  String blogEdit(@PathVariable(value = "id") long id, Model model){
        postService.findPostById(id);
        model.addAttribute("post", postService.getPost());
        return "postEdit";
    }

    @GetMapping("/blog/{id}/editPost/tag")
    public  String blogEditTag(Model model){
        model.addAttribute("post", postService.getPost());
        return "postEdit";
    }

    @PostMapping("/blog/{id}/editPost/tag")
    public String blogPostUpdateTag(@RequestParam(value="action", required=false) String action, @RequestParam String subjectPost, @RequestParam String anonsPost, @RequestParam String fullTextPost,@RequestParam String tag, Model model){
        postService.blogPostUpdate( subjectPost,   anonsPost,   fullTextPost,  tag);
        model.addAttribute("post", postService.getPost());

        if (action!=null && action.equals("update")) {
            postService.saveAndResetPost();
            return "redirect:/blog";
        }

        return "postEdit";
    }

    ////!!
    @GetMapping("/blog/addPost")
    public  String blogAdd(Model model){
        model.addAttribute("post", postService.getPost());
        return "postAdd";
    }

    @PostMapping("/blog/addPost")
    public String blogPostAdd(
            @AuthenticationPrincipal User user,
            @RequestParam(value="action", required=false) String action,
                              @RequestParam String subjectPost,
                              @RequestParam String anonsPost,
                              @RequestParam String fullTextPost,
                              @RequestParam String tag, Model model){
        postService.blogPostUpdate( subjectPost,   anonsPost,   fullTextPost,  tag);

        model.addAttribute("post", postService.getPost());

        if (action!=null && action.equals("savePost")) {
            postService.getPost().setAuthor(user.getAuthor());
            postService.saveAndResetPost();
            return "redirect:/blog";
        }
        return "postAdd";
    }

    @GetMapping("/blog/addTag/{name}/{shortDescription}")
    public String blogAddTagPost(@PathVariable(value = "name") String name, @PathVariable(value = "shortDescription") String shortDescription){
        tagService.addTag(name,shortDescription);
        return "redirect:/blog";
    }

    @GetMapping("/blog/addPost/removeTag/{idTag}")
    public String deleteTagInPostById(@PathVariable(value = "idTag") long id){
        postService.getPost().getTags().remove(tagService.findTagById(id));
        return "redirect:/blog/addPost";
    }


    @GetMapping("/blog/editPost/removeTag/{idTag}")
    public String deleteTagInEditPostById(@PathVariable(value = "idTag") long id){
        postService.getPost().getTags().remove(tagService.findTagById(id));
        return "redirect:/blog/"+ postService.getPost().getId()+ "/editPost/tag";
    }
}