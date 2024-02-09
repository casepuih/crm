package com.example.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.crm.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    UserDetails findByEmail(String username);

    List<User> findByToken(String token);

    long countByTokenLike(String token);
}
