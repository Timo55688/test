package com.test.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.test.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String showUsers(Model model) {
        try {
            Map<String, Object> result = userService.fetchUsers();
            model.addAttribute("users", result.get("users"));
            model.addAttribute("pagination", result.get("pagination"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "userList";
    }
}
