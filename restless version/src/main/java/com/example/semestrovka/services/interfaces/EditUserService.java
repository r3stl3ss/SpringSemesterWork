package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.ProfileUsernameEditForm;
import com.example.semestrovka.dto.forms.ProfilePasswordEditForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EditUserService {
    public boolean editUsername(ProfileUsernameEditForm form, UserDto userDto);

    public boolean editPassword(ProfilePasswordEditForm form, UserDto userDtp);

    public void editImage(UserDto userDto, MultipartFile file) throws IOException;


}
