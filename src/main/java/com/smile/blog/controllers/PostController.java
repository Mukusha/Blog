package com.smile.blog.controllers;

import com.smile.blog.models.Post;
import com.smile.blog.models.Tag;
import com.smile.blog.models.User;
import com.smile.blog.services.PostService;
import com.smile.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Controller
public class PostController {

    private final PostService postService;
    private final TagService tagService;

    @Autowired
    public PostController(PostService postService, TagService tagService) {
        this.postService = postService;
        this.tagService = tagService;
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

    @GetMapping("/blog/addPost")
    public  String blogAdd(Model model){
        postService.nullPost();
        model.addAttribute("post", postService.getPost());
        model.addAttribute("isAddNewTag", false);
        return "postAdd";
    }

    @PostMapping("/blog/addPost")
    public String blogPostAdd(
            @AuthenticationPrincipal User user,
            @RequestParam(value="action", required=false) String action,
                              @RequestParam String subjectPost,
                              @RequestParam String anonsPost,
                              @RequestParam String fullTextPost,
                              @RequestParam String tag,
                              @RequestParam String nameTag,
                              @RequestParam String shortDescription,
                              @RequestParam(required=false) String nfTagName, Model model){

        postService.blogPostUpdate( subjectPost,   anonsPost,   fullTextPost,  tag);

        model.addAttribute("post", postService.getPost());
        model.addAttribute("isAddNewTag", false);

       if (tagService.findTagByName(tag)==null) {
           model.addAttribute("isAddNewTag",true);
           model.addAttribute("nameTag",tag);
       }

        if (action!=null && action.equals("addNewTag")) {
            tagService.addTag(nameTag,shortDescription);
            postService.blogPostUpdate(subjectPost,   anonsPost,   fullTextPost,nameTag);
            model.addAttribute("isAddNewTag",false);
            return "postAdd";
        }

        if (action!=null && action.equals("cancelNewTag")) {
            model.addAttribute("isAddNewTag",false);
            return "postAdd";
        }

        if (action!=null && action.equals("findNewTag")) {
            model.addAttribute("nameTag",nameTag);
            model.addAttribute("isFindTag",true);
            model.addAttribute("tags",tagService.findAnalogTagByName(nameTag));
            tagService.setTags( tagService.findAnalogTagByName(nameTag));
            return "postAdd";
        }

        if (action!=null && action.equals("savePost")) {
            postService.getPost().setAuthor(user.getAuthor());
            postService.saveAndResetPost();
            return "redirect:/blog";
        }

        return "postAdd";
    }


    @GetMapping("/blog/addPost/removeTag/{idTag}")
    public String deleteTagInPostById(@PathVariable(value = "idTag") long id, Model model){
        postService.getPost().getTags().remove(tagService.findTagById(id));
        model.addAttribute("post", postService.getPost());
        model.addAttribute("isAddNewTag", false);
        return "postAdd";
    }

    @GetMapping("/blog/addPost/addFindTag/{idTag}")
    public String addFindTag(@PathVariable(value = "idTag") long id,Model model){
       tagService.deleteLocTag(tagService.findTagById(id));
        Set<Tag> tags = new HashSet<>();
        if(postService.getPost().getTags()!=null) tags.addAll(postService.getPost().getTags()) ;
        tags.add(tagService.findTagById(id));
        postService.getPost().setTags(tags);
        model.addAttribute("post", postService.getPost());
        System.out.println(tagService.getTags().size());
        if (tagService.getTags().size()==0){
            model.addAttribute("isAddNewTag", false);
            model.addAttribute("isFindTag", false);
        } else {
            model.addAttribute("isAddNewTag", true);
            model.addAttribute("isFindTag", true);
            model.addAttribute("tags", tagService.getTags());
        }

        return "postAdd";
    }
    @GetMapping("/blog/editPost/removeTag/{idTag}")
    public String deleteTagInEditPostById(@PathVariable(value = "idTag") long id){
        postService.getPost().getTags().remove(tagService.findTagById(id));
        return "redirect:/blog/"+ postService.getPost().getId()+ "/editPost/tag";
    }
}