package com.example.semestrovka.security.jwt;

import com.example.semestrovka.security.details.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class JwtAuthentication implements Authentication {

    private boolean isAuthenticated;
    private String token;
    private UserDetailsImpl ud;

    public JwtAuthentication(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return ud.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return ud.getPassword();
    }

    @Override
    public Object getDetails() {
        return ud;
    }

    @Override
    public Object getPrincipal() {
        return ud.getUser();
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return null;
    }

    public void setUserDetails(UserDetails ud) {
        this.ud = (UserDetailsImpl)ud;
    }
}

