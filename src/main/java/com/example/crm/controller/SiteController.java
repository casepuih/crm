package com.example.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SiteController {
    
    @GetMapping("")
    public String home() {
        return "home";
    }
}
