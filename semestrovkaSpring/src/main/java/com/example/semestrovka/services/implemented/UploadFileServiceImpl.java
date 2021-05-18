package com.example.semestrovka.services.implemented;

import com.example.semestrovka.repositories.FilesRepository;
import com.example.semestrovka.services.interfaces.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    @Autowired
    private FilesRepository filesRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void uploadFile(MultipartFile file) throws IOException {
        if (file != null) {
            File uploadFolder = new File(uploadPath);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String finalFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + File.separator + finalFileName));

        }
    }

}
