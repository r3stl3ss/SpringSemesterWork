package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signUp(SignUpForm form) {
        User newUser = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .username(form.getUsername())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .role(User.Role.USER)
                .state(User.State.ACTIVE)
                .build();

        Optional<User> userFromBase = usersRepository.findByUsername(form.getUsername());

        if (userFromBase.isPresent()) {
            return false;
        }
        usersRepository.save(newUser);
        return true;
    }
}

