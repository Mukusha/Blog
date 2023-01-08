package com.smile.blog.controllers;

import com.smile.blog.models.Role;
import com.smile.blog.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String adminDeleteUser(@PathVariable(value = "userId") Long userId, Model model){
        userService.DeleteUser(userId);
        model.addAttribute("allUsers", userService.getAllUsers());
        return "adminUsers";
    }

    @GetMapping("/admin/roles")
    public  String adminRolesPage(Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", Role.values());
        model.addAttribute("noDisablet",userService.getDisabletList(-1));
        return "adminRoles";
    }



    @PostMapping("/admin/roles/{userId}/edit")
    public  String rolesEdit(@PathVariable(value = "userId") Long userId, Model model){
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", Role.values());
        model.addAttribute("noDisablet",userService.getDisabletList(userId));
        return "adminRoles";
    }

    @PostMapping("/admin/roles/{userId}/save")
    public  String rolesSaveEdit(@PathVariable(value = "userId") Long userId,
                                 @RequestParam(required=false) Boolean ADMIN,
                                 @RequestParam(required=false) Boolean USER,
                                 Model model) throws Exception {
        //сохранить новое распределение ролей
        userService.editRoleUser(userId, ADMIN, USER);

        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("roles", Role.values());
        model.addAttribute("noDisablet",userService.getDisabletList(-1));
        return "adminRoles";
    }
}
