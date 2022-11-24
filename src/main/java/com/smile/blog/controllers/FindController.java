package com.smile.blog.controllers;

import com.smile.blog.services.FindService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FindController {
    private final FindService findService;

    public FindController(FindService findService) {
        this.findService = findService;
    }

    @GetMapping("/blog/find")
    public  String getFind(Model model){
        model.addAttribute("posts",null);
        model.addAttribute("authors",null);
        return "find";
    }

    @PostMapping("/blog/findPost")
    public  String findPost(@RequestParam String subjectPost,
                            @RequestParam String anonsPost,
                            @RequestParam String fullTextPost,
                            @RequestParam String author,Model model){

        model.addAttribute("subjectPost",subjectPost);
        model.addAttribute("anonsPost",anonsPost);
        model.addAttribute("fullTextPost",fullTextPost);
        model.addAttribute("author",author);
       // model.addAttribute("authors",  findService.findAuthor(author, ""));
        model.addAttribute("posts", findService.findKeys(subjectPost,anonsPost,fullTextPost,author));
        model.addAttribute("infoIf","post");
        return "find";
    }

    @PostMapping("/blog/findAuthor")
    public  String findAuthor(@RequestParam String nickname,
                            @RequestParam String shortInformation,
                            Model model){

        model.addAttribute("nickname",nickname);
        model.addAttribute("shortInformation",shortInformation);
        model.addAttribute("authors", findService.findAuthor(nickname, shortInformation));
      //  model.addAttribute("posts", null);
        model.addAttribute("infoIf","author");
        return "find";
    }

    @PostMapping("/blog/findTag")
    public  String findTag(@RequestParam String name,
                            @RequestParam String shortDescription,
                            Model model){

        model.addAttribute("name",name);
        model.addAttribute("shortDescription",shortDescription);
        model.addAttribute("infoIf","tag");
        model.addAttribute("tags", findService.findTag(name, shortDescription));
       // model.addAttribute("posts", null);
        return "find";
    }

}
