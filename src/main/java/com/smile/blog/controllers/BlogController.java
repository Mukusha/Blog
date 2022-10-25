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

@Controller
public class BlogController {

    static Post post = new Post(null, "Введите название статьи","Введите анонс статьи","Введите полный текст статьи",null);
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

    @GetMapping("/blog/addPost")
    public  String blogAdd(Model model){
        model.addAttribute("post", post);
        if (TagController.tags!=null)        model.addAttribute("tags", TagController.tags);
        return "postAdd";
    }

    @GetMapping("/blog/addPost/tag")
    public  String blogAddTag(Model model){
        model.addAttribute("post", post);
        if (TagController.tags!=null)        model.addAttribute("tags", TagController.tags);
        return "postAdd";
    }

    @PostMapping("/blog/addPost/tag")
    public String blogPostAdd(@RequestParam String tag,@RequestParam String subjectPost, @RequestParam String anonsPost, @RequestParam String fullTextPost){
        //пока не указываем автора, он будет указываться в зависимости от профиля пользователя
        Author author = new Author("Анохина Алефтина Тимофеевна","Anna","");

       if (subjectPost!="") BlogController.post.setSubjectPost(subjectPost);
        if (anonsPost!="") BlogController.post.setAnonsPost(anonsPost);
        if (fullTextPost!="") BlogController.post.setFullTextPost(fullTextPost);

        Tag tagNew = tagService.findTagByName(tag);
        if (tagNew!=null)       TagController.tags.add(tagNew);
      return "redirect:/blog/addPost/tag";
    }

    @PostMapping("/blog/addPost")
    public String blogPostAdd(){
        //пока не указываем автора, он будет указываться в зависимости от профиля пользователя
        Author author = new Author("Анохина Алефтина Тимофеевна","Anna",""); //пример
        post.setTags(TagController.tags);
      // postService.postAdd(author,  post.getSubjectPost(),  post.getAnonsPost(),  post.getFullTextPost(),TagController.tags);
     postService.postAdd(post);
     post=new Post(null, "Введите название статьи","Введите анонс статьи","Введите полный текст статьи",null);
        TagController.tags = new HashSet<>();
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
