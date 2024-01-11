package com.projetsynthese.back_citizen_manager.controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    @PostMapping("/api/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "No file uploaded";
        }

        try {
            // Get the file name
            String fileName = file.getOriginalFilename();

            // Create a new file in a specific location
            String uploadPath = "../../../resources/upload/";
            File uploadDirectory = new File(uploadPath);
            if (!uploadDirectory.exists()) {
                uploadDirectory.mkdirs();
            }
            File destinationFile = new File(uploadPath + fileName);
            file.transferTo(destinationFile);

            // Handle further processing or save the file information in a database

            return "File uploaded successfully";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error uploading file: " + e.getMessage();
        }
    }
}
