package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.File;
import com.example.crm.repository.FileRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public List<File> getAll(){
        return fileRepository.findAll();
    }

    public File getById(@NonNull Long id){
        return fileRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find File with ID: " + id)); 
    }

    public File create(@NonNull File file){
        return fileRepository.save(file);
    }
}