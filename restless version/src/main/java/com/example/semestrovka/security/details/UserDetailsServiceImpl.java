package com.example.semestrovka.security.details;

import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("customUDS")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository ur;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = ur.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with such nickname not found"));
        return new UserDetailsImpl(user);
    }
}
