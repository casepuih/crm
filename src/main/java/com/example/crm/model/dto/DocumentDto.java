package com.example.crm.model.dto;

import java.sql.Date;
import java.util.List;

import com.example.crm.model.Document;
import com.example.crm.model.File;
import com.example.crm.model.Tag;

import lombok.Data;

@Data
public class DocumentDto {
    private Long id;
    private String title;
    private String description;
    private Date createdAt;
    private List<File> files;
    private Long category;
    private List<Tag> tags;

    public DocumentDto(Document doc){
        id = doc.getId();
        title = doc.getTitle();
        description = doc.getDescription();
        createdAt = doc.getCreatedAt();
        files = doc.getFiles();
        category = doc.getCategory().getId();
        tags = doc.getTags();
    }
}
