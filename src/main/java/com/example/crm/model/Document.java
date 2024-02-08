package com.example.crm.model;

import java.sql.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @CreatedDate
    private Date created_at;

    @ManyToOne
    private User user;

    @OneToMany
    private List<File> files;
    
    @ManyToOne
    private Category category;

    @ManyToMany
    private List<Tag> tags;
}
