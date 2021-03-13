package com.example.semestrovka.services.interfaces;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadFileService {
    public void uploadFile(MultipartFile file) throws IOException;
}
