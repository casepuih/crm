package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.Document;
import com.example.crm.repository.DocumentRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> getAll(){
        return documentRepository.findAll();
    }

    public Document getById(@NonNull Long id){
        return documentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find Document with ID: " + id)); 
    }

    public Document create(@NonNull Document document){
        return documentRepository.save(document);
    }
}