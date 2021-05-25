package com.example.semestrovka.services.implemented;

import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email).get();
    }

    @Override
    public User getUserById(Long id) {
        return usersRepository.findById(id).get();
    }

    @Override
    public boolean existsByUsername(String username) {
        return usersRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usersRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return usersRepository.existsByPhone(phone);
    }

}

