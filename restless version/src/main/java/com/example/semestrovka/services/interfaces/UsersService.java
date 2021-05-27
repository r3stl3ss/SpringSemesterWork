package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    public List<User> getAllUsers();

    public User getUserByEmail(String email);

    public User getUserById(Long id);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public boolean existsByPhone(String phone);

    public User findByUsername(String username);

    public User findById(Long id);

    void subscribe(String subscriberUsername, Long publisherId);

    public void unsubscribe(String subscriberUsername, Long publisherId);
}
