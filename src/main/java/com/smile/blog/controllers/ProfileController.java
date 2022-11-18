package com.smile.blog.controllers;

import com.smile.blog.models.Author;
import com.smile.blog.models.User;
import com.smile.blog.services.AuthorService;
import com.smile.blog.services.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class ProfileController {

    private final AuthorService authorService;
    private final PostService postService;

    public ProfileController(AuthorService authorService, PostService postService) {
        this.authorService = authorService;
        this.postService = postService;
    }

    @GetMapping("/blog/profile/{id}")
    public  String profileDetails(@PathVariable(value = "id") long id,Model model){
        // кнопка редактировать появляется только для хозяина страницы
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        model.addAttribute("posts", postService.getAllPostAuthor(author));
        return "profileDetails";
    }

    @PostMapping("/blog/profile/{id}")
    public String profileDetailsPost(@PathVariable(value = "id") long id, Model model){
        //обработка кнопок редактировать и удалить
        return "profileDetails";
    }

    @GetMapping("/blog/profile/{id}/edit")
    public  String profileEdit(
            @AuthenticationPrincipal User user,
            @PathVariable(value = "id") long id,Model model){
        // доступ имеет только автор страницы
        if(user.getAuthor().getId() != id)
        {
            System.out.println("вы не хозяин страницы");
            return "redirect:/blog/profile/"+id;} //не даем перейти на страницу редактирования
        //
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        model.addAttribute("age", author.getAge());
        return "profileDetailsEdit";
    }

    @PostMapping("/blog/profile/{id}/edit")
    public String profileEditSave(
            @PathVariable(value = "id") long id,
            @RequestParam String nickname,
            @RequestParam String shortInformation,
            @RequestParam String dateOfBirth, Model model) throws ParseException {
        authorService.AuthorSaveUpdate(id, nickname,   shortInformation, dateOfBirth);
        model.addAttribute("author", authorService.findAuthorById(id));
        return "redirect:/blog/profile/"+id; //возвращаемся на страницу отредактированного профиля
    }
}
