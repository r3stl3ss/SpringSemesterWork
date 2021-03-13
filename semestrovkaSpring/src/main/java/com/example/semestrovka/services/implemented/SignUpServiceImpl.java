package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void signUp(SignUpForm form) {
        User newUser = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .username(form.getUsername())
                .password(form.getPassword())
                .build();

        usersRepository.save(newUser);
    }
}

