package com.example.crm.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadInfo {

    private boolean erreur;
    private String message;
    private String url;

}