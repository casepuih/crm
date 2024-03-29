package com.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.crm.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

    List<Role> findByNameLikeIgnoreCase(String name);
    
}
