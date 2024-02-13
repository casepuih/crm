package com.example.crm.model.dto;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.example.crm.model.Category;
import com.example.crm.model.Role;
import com.example.crm.model.User;

import lombok.Data;

@Data
public class UserDto {
    private Long id;  
    private String lastName;
    private String firstName;
    private String email;
    private Boolean active;
    private Date createdAt;
    private Role role;
    private List<Category> categories;
    private List<DocumentDto> documents;

    public UserDto(User user){
        id = user.getId();
        lastName = user.getLastName();
        firstName = user.getFirstName();
        email = user.getEmail();
        active = user.getActive();
        createdAt = user.getCreatedAt();
        role = user.getRole();
        categories = user.getCategories();

        documents = new ArrayList<>();
        user.getDocuments().forEach(doc -> documents.add(new DocumentDto(doc)));
    }
}
