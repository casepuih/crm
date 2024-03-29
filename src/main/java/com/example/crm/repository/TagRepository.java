package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.model.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long>{
    
}
