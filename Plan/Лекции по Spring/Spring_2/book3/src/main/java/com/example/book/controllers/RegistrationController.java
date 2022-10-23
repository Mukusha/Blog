package com.example.book.controllers;

import com.example.book.models.User;
import com.example.book.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController( UserService userService)
    { this.userService = userService;}

    //будет возвращать форму регистрации
    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    //для сохранения формы регистрации
    @PostMapping("/registration")
    public String adduser(User user, Model model)
    {
        try
        {
            userService.addUser(user);
            return "redirect:/login";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

}
