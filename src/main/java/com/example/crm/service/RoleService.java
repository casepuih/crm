package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.Role;
import com.example.crm.repository.RoleRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role getById(@NonNull Long id){
        return roleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find Role with ID: " + id)); 
    }

    public Role create(@NonNull String name){
        // Check if role already exists 
        List<Role> role = roleRepository.findByNameLikeIgnoreCase(name);
        if (role.size() >= 1) {
            return role.get(0);
        }

        Role newRole = new Role();
        newRole.setName(name);

        return roleRepository.save(newRole);
    }
}