package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.Category;
import com.example.crm.model.User;
import com.example.crm.repository.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public List<Category> getAllByUser(@NonNull Long id){
        User user = userService.getById(id);
        return user.getCategories();
    }

    public Category getById(@NonNull Long id){
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find Category with ID: " + id)); 
    }

    public Category create(@NonNull Category category){
        return categoryRepository.save(category);
    }
}