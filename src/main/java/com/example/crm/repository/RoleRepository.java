package com.example.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
}
