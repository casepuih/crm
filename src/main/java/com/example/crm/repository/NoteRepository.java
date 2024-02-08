package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    
}
