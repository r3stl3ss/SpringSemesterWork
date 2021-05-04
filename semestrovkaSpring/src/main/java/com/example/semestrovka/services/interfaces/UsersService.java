package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.SignUpForm;
import org.springframework.data.domain.Page;

public interface UsersService {
    Page<UserDto> getUsers(int number);

    UserDto getUser(Long id);

    UserDto createUser(SignUpForm form);

    UserDto updateUser(Long id, SignUpForm form);

    void deleteUser(Long id);
}