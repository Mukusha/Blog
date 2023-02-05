package com.smile.blog.controllers;

import com.smile.blog.models.User;
import com.smile.blog.services.UserService;
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

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String adduser(User user, Model model)
    {
        try
        {
            userService.addUser(user);
            return "redirect:/blog";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", "Данный логин уже занят ");
            return "registration";
        }
    }



}
