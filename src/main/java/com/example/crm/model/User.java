package com.example.crm.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private Boolean active;
    private String token;
    

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    @JsonIgnoreProperties("documents")
    private List<Document> documents = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return active;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", email='" + email + '\'' +
            ", active=" + active +
            ", token='" + token + '\'' +
            ", createdAt=" + createdAt +
            ", role=" + (role != null ? role.getName() : null) + // Assuming Role has a getName() method
            ", documentCount=" + (documents != null ? documents.size() : 0) + // Count of documents
            '}';
        }
}
