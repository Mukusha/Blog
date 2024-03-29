package com.smile.blog.controllers;

import com.smile.blog.models.Author;
import com.smile.blog.models.Role;
import com.smile.blog.models.User;
import com.smile.blog.services.AuthorService;
import com.smile.blog.services.PostService;
import com.smile.blog.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class ProfileController {

    private final AuthorService authorService;
    private final PostService postService;
    private final UserService userService;

    public ProfileController(AuthorService authorService, PostService postService, UserService userService) {
        this.authorService = authorService;
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping("/blog/profile/{id}")
    public  String profileDetails(@AuthenticationPrincipal User user,
                                  @PathVariable(value = "id") long id,Model model){
        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        model.addAttribute("age", author.getAge());
        model.addAttribute("posts", postService.getAllPostAuthor(author));
        model.addAttribute("email", userService.findEmailByAuthorId(id));
        model.addAttribute("act", userService.isAccountActivatedByAuthorId(id));

        //своя ли страница не своя не даем перейти на страницу редактирования
        if(user.getAuthor().getId() == id) {
            model.addAttribute("my", true);
            if(user.getRoles().contains(Role.ADMIN))  model.addAttribute("isAdmin", true);}
        else model.addAttribute("my", false);
        if(userService.getRolesAuthor(id).contains(Role.ADMIN)) model.addAttribute("isPageAdmin", true);
        return "profileDetails";
    }

    @GetMapping("/profile/{name}")
    public  String profileDetailsByUserName(@AuthenticationPrincipal User user,
                                            @PathVariable(value = "name") String name,
                                            Model model){
        //! кнопка редактировать появляется только для хозяина страницы
        Author author = authorService.findAuthorById(userService.findAuthorIdByUsername(name));
        model.addAttribute("author", author);
        model.addAttribute("age", author.getAge());
        model.addAttribute("posts", postService.getAllPostAuthor(author));
        model.addAttribute("posts", postService.getAllPostAuthor(author));
        model.addAttribute("my", true);
        model.addAttribute("email", userService.findEmailByAuthorId(author.getId()));
        model.addAttribute("act", userService.isAccountActivatedByAuthorId(author.getId()));

        if(user.getRoles().contains(Role.ADMIN)) { model.addAttribute("isAdmin", true);
            model.addAttribute("isPageAdmin", true);
        }
        return "profileDetails";
    }

    @GetMapping("/blog/profile/{id}/edit")
    public  String profileEdit(
            @AuthenticationPrincipal User user,
            @PathVariable(value = "id") long id,
            Model model){
        // доступ имеет только автор страницы
        if(user.getAuthor().getId() != id) return "redirect:/blog/profile/"+id;

        Author author = authorService.findAuthorById(id);
        model.addAttribute("author", author);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(author.getDateOfBirth());
        model.addAttribute("dateOfBirth", date);
        if(user.getRoles().contains(Role.ADMIN)) { model.addAttribute("isAdmin", true);
            model.addAttribute("isPageAdmin", true);
        }
        return "profileDetailsEdit";
    }

    @PostMapping("/blog/profile/{id}/edit")
    public String profileEditSave(
            @RequestParam(name="file", required=false) MultipartFile file,
            @PathVariable(value = "id") long id,
            @RequestParam String nickname,
            @RequestParam String shortInformation,
            @RequestParam String dateOfBirth, Model model) throws ParseException, IOException {

        authorService.AuthorSaveUpdate(id, nickname,   shortInformation, dateOfBirth, file);
        model.addAttribute("author", authorService.findAuthorById(id));
        return "redirect:/blog/profile/"+id; //возвращаемся на страницу отредактированного профиля
    }

    @PostMapping("/blog/link_mail")
    public String addEmeil(
            @AuthenticationPrincipal User user,
            @RequestParam String email)
    {
        try
        {
            userService.addEmail(user, email);
            return "redirect:/blog/profile/"+user.getAuthor().getId();
        }
        catch (Exception ex)
        {
            return "redirect:/blog/profile/"+user.getAuthor().getId();
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);

        if (isActivated) {
            model.addAttribute("message", "User успешно активирован");
        } else {
            model.addAttribute("message", "Код активации не найден!");
        }

        return "login";
    }
}
