package com.example.crm.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.crm.model.User;
import com.example.crm.service.RoleService;
import com.example.crm.service.UserService;


@Controller
public class SiteController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    
    @GetMapping("/")
    public String home(ModelMap map, @AuthenticationPrincipal User user) {
        map.put("user", user);
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }
    

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) {
        // Check if email already exists 
        try {
            userService.loadUserByUsername(username);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This email exists already");
        }

        // Validation 
        User newUser = new User();
        newUser.setEmail(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setActive(true);
        newUser.setRole(roleService.create("USER"));

        userService.create(newUser);
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboardPage(ModelMap map, @AuthenticationPrincipal User userAuth) {
        String  username = userAuth.getUsername();
        User user = userService.findByEmail(username);
        map.put("categories", user.getCategories());
        return "documents";
    }
}