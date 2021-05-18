package com.example.semestrovka.services.implemented;

import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

}
