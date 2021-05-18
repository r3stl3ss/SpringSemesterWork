package com.example.semestrovka.services.implemented;

import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.MakeUserOfDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MakeUserOfDetailsServiceImpl implements MakeUserOfDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public User fill(UserDetails ud) {
        return usersRepository.findByUsername(ud.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }
}
