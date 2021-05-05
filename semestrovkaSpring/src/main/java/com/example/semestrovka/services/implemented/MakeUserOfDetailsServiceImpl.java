package com.example.semestrovka.services.implemented;

import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.MakeUserOfDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MakeUserOfDetailsServiceImpl implements MakeUserOfDetailsService {
    private UsersRepository ur;
    @Override
    public User fill(UserDetails ud) {
        return ur.findByUsername(ud.getUsername()).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
    }
}
