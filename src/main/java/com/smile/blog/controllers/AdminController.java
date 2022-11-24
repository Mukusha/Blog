package com.smile.blog.controllers;

import com.smile.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/users")
    public  String adminUserPage(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        return "adminUsers";
    }

    @PostMapping("/admin/users/{userId}/delete")
    public String adminDeleteUser(@PathVariable(value = "userId") Long userId){
        userService.DeleteUser(userId);
        return "redirect:/admin/users";
    }
}
