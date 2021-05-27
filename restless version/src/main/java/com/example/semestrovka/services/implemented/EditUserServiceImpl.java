package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.ProfileUsernameEditForm;
import com.example.semestrovka.dto.forms.ProfilePasswordEditForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.EditUserService;
import com.example.semestrovka.services.interfaces.FileService;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class EditUserServiceImpl implements EditUserService {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final UsersService usersService;

    private final FileService filesService;

    @Autowired
    private final UsersRepository usersRepository;

    @Override
    public boolean editUsername(ProfileUsernameEditForm form, UserDto userDto) {

        if (passwordEncoder.matches(form.getPassword(),usersService.getUserByEmail(userDto.getEmail()).getHashPassword())) {
            userDto.setUsername(form.getUsername());
            User user = usersService.getUserByEmail(userDto.getEmail());
            user.setUsername(form.getUsername());
            usersRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean editPassword(ProfilePasswordEditForm form, UserDto userDto) {
        String newPassword = passwordEncoder.encode(form.getNewPassword());
        String repeatNewPassword = form.getRepeatNewPassword();
        if (passwordEncoder.matches((form.getOldPassword()),usersService.getUserByEmail(userDto.getEmail()).getHashPassword())&&
                passwordEncoder.matches(repeatNewPassword,
                        newPassword)){
            User user = usersService.getUserByEmail(userDto.getEmail());
            user.setHashPassword(newPassword);
            usersRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void editImage(UserDto userDto, MultipartFile file) throws IOException {
        filesService.addProfileImage(userDto, file);
    }

}
