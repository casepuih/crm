package com.example.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.example.crm.model.Note;
import com.example.crm.repository.NoteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAll(){
        return noteRepository.findAll();
    }

    public Note getById(@NonNull Long id){
        return noteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find Note with ID: " + id)); 
    }

    public Note create(@NonNull Note note){
        return noteRepository.save(note);
    }
}