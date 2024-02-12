package com.example.crm.controller;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.crm.model.FileUploadInfo;

@RestController
public class FileController {

    @Value("${file.upload.path}")
    private String pathUploadFile;

    @PostMapping("/file/upload")
    public FileUploadInfo upload(@RequestParam MultipartFile file){
        File directory = new File(this.pathUploadFile);

        // vérifier si le répertoire n'existe pas on va le créer avec la commande mkdir
        if (!directory.exists()){
            directory.mkdirs(); // pour le créer
        }


        // file.getOriginalFilename()) => nom d'origine du fichier a uploader
        try{
            String fileName = file.getOriginalFilename(); // mon.Image1.png ou monImage1.webp
            if (fileName == null){
                return new FileUploadInfo(true, "Nom de fichier vide", null);
            }
            int positionPoint = fileName.lastIndexOf(".");
            // copier le fichier du répertoire tmp vers le repertoire upload
            String extensions = fileName.substring(positionPoint); // ".png"
            List<String> extAutoriser = List.of(".png",".jpg",".webp",".svg",".jpeg", ".pdf");
            if (!extAutoriser.contains(extensions.toLowerCase())){ // toLowerCase() => miniscule
                return new FileUploadInfo(true, "Extension non autorisée", null);
            }

            String uniqueId = UUID.randomUUID().toString();
            fileName = uniqueId + extensions;
            file.transferTo(new File(directory.getAbsolutePath(), fileName));
            return new FileUploadInfo(false, null, fileName);
        } catch (Exception ex){
            //ex.printStackTrace();
            return new FileUploadInfo(true, ex.getMessage(), null);
        }
    }

    @GetMapping("/file/image/{fileName}")
    public ResponseEntity<?> displayImage(@PathVariable String fileName){
        // pathUploadFile => Chemin que l'image et sauvegarder
        File file = new File(pathUploadFile, fileName);
        if (!file.exists()){
            return null;
        }
        try{
            byte[] imageData = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.valueOf("image/png")).body(imageData);
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
