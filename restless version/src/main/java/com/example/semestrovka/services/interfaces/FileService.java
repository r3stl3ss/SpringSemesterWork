package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.ReviewDto;
import com.example.semestrovka.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    public void addProfileImage(UserDto userDto, MultipartFile file) throws IOException;

    public String createFile(String uploadPath, MultipartFile file) throws IOException;
}
