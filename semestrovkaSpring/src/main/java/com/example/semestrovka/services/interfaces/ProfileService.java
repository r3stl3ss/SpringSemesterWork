package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
     List<User> getAllUsers();

}
