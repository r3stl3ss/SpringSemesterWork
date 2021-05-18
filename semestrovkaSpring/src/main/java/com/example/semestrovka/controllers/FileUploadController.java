package com.example.semestrovka.controllers;

import com.example.semestrovka.services.interfaces.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class FileUploadController {

    @Autowired
    private UploadFileService uploadFileService;

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
            uploadFileService.uploadFile(file);
        } else {
            model.put("message", "file wrong");
        }

        return "redirect:/upload";
    }
}
