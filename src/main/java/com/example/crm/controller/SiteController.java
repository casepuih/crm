package com.example.crm.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.crm.model.Category;
import com.example.crm.model.User;
import com.example.crm.model.dto.UserDto;
import com.example.crm.service.CategoryService;
import com.example.crm.service.DocumentService;
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

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DocumentService documentService;
    
    @GetMapping("/dashboard")
    public String home(ModelMap map, @AuthenticationPrincipal User userAuth) {
        String  username = userAuth.getUsername();
        User user = userService.findByEmail(username);
        UserDto userDto = new UserDto(user);
        List<Category> categories = userDto.getCategories();
        map.put("user", user);
        map.put("categories", categories);
        return "documents";
    }

    @GetMapping("/login")
    public String login() {
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
        return "redirect:/";
    }

    // @GetMapping("/dashboard")
    // public String dashboardPage(ModelMap map, @AuthenticationPrincipal User userAuth) {
    //     String  username = userAuth.getUsername();
    //     User user = userService.findByEmail(username);
    //     map.put("categories", user.getCategories());
    //     return "documents";
    // }

    @GetMapping("/{category_id}/documents")
    public String showDocuments(ModelMap map, @AuthenticationPrincipal User userAuth, @NonNull @PathVariable Long category_id) {
        String  username = userAuth.getUsername();
        User user = userService.findByEmail(username);
        map.put("user", user);
        map.put("category", categoryService.getById(category_id));
        map.put("categories", user.getCategories());
        map.put("docs", user.getDocuments());
        return "documents";
    } 

    @GetMapping("/{category_id}/documents/{document_id}")
    public String documentDetail(ModelMap map, @AuthenticationPrincipal User userAuth, @NonNull @PathVariable Long category_id, @NonNull @PathVariable Long document_id) {
        String  username = userAuth.getUsername();
        User user = userService.findByEmail(username);
        map.put("user", user);
        map.put("category", categoryService.getById(category_id));
        map.put("categories", user.getCategories());
        map.put("docs", user.getDocuments());
        map.put("document", documentService.getById(document_id));
        return "documents";
    } 
}