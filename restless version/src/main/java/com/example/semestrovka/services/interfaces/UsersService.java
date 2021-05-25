package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.models.User;

import java.util.List;

public interface UsersService {
    public List<User> getAllUsers();

    public User getUserByEmail(String email);

    public User getUserById(Long id);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public boolean existsByPhone(String phone);
}
