package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.crm.model.User;
import com.example.crm.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService implements UserDetailsService {
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

    public User findByToken(String token) {
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1){
            return null;
        }
        return users.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }

    public boolean isTokenExiste(String token) {
        return userRepository.countByTokenLike(token) > 0;
    }
}