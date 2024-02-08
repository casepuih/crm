package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.User;
import com.example.crm.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(@NonNull Long id){
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find User with ID: " + id)); 
    }

    public User create(@NonNull User user){
        return userRepository.save(user);
    }
}