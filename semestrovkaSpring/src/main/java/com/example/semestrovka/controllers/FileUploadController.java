package com.example.semestrovka.controllers;

import com.example.semestrovka.models.UploadableFile;
import com.example.semestrovka.repositories.FilesRepository;
import com.example.semestrovka.services.interfaces.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Autowired
    private UploadFileService ufs;

    @GetMapping("/upload")
    public String getUploadPage() {
        return "fileUploadPage";
    }

    @PostMapping("/upload")
    public String uploadFile(
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException
    {
        if (!file.isEmpty()) {
            ufs.uploadFile(file);
        } else {
            model.put("message", "file wrong");
        }

        return "redirect:/upload";
    }
}
