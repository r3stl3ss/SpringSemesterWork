package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.FilesRepository;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.FileService;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {


    @Value("${upload.path}")
    private String uploadPath;

    private final UsersService usersService;

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public void addImage(UserDto userDto, MultipartFile file) throws IOException {
        if (file != null) {
            String resultFileName = createFile(uploadPath,file);
            User user = usersService.getUserById(userDto.getId());
            user.setPathToAvatar(resultFileName);
            usersRepository.save(user);
        }
    }

    @Override
    public String createFile(String uploadPath, MultipartFile file) throws IOException {
        File uploadDirectory = new File(uploadPath);

        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdir();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        String finalFileName = uploadPath + "/" + resultFileName;
        file.transferTo(new File(finalFileName));
        return resultFileName;
    }
}
