package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.Tag;
import com.example.crm.repository.TagRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> getAll(){
        return tagRepository.findAll();
    }

    public Tag getById(@NonNull Long id){
        return tagRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find Tag with ID: " + id)); 
    }

    public Tag create(@NonNull Tag tag){
        return tagRepository.save(tag);
    }
}