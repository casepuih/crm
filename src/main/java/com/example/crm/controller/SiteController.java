package com.example.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.crm.service.CategoryService;

@Controller
public class SiteController {
    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("")
    public String home(ModelMap map) {
        map.put("categories", categoryService.getAll());
        return "home";
    }
}
