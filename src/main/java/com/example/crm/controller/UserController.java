package com.example.crm.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.crm.model.User;


@Controller
@RequestMapping("/user")
public class UserController {
    
    @GetMapping("")
    public String userHomepage(@AuthenticationPrincipal User user) {
        return "user";
    }
}