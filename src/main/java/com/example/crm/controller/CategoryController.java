package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.crm.model.Category;
import com.example.crm.model.User;
import com.example.crm.service.CategoryService;
import com.example.crm.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addCategory(@RequestParam String newCategoryName, @AuthenticationPrincipal User userConnected) {
        User user = userService.findByEmail(userConnected.getUsername());
        Category cat = new Category();
        cat.setName(newCategoryName);
        categoryService.create(cat);

        userService.addCategory(user, cat);
        
        return "redirect:/dashboard";
    }
    
}
