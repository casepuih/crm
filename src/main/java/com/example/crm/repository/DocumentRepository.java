package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
    
}
