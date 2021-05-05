package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.models.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface MakeUserOfDetailsService {
    public User fill(UserDetails ud);
}
