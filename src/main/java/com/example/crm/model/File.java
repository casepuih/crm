package com.example.crm.model;

import java.sql.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   

    private String name;
    private String url;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private Document document;

    @CreatedDate
    private Date createdAt;
}
