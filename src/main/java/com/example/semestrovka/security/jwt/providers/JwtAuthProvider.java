package com.example.semestrovka.security.jwt.providers;

import com.example.semestrovka.models.User;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.security.jwt.JwtAuthentication;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthProvider implements AuthenticationProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad Token");
        }
        User user = User.builder()
                .id(Long.parseLong(claims.get("sub", String.class)))
                .username(claims.get("username", String.class))
                .role(User.Role.valueOf(claims.get("role", String.class)))
                .build();
        UserDetails userDetails = UserDetailsImpl.builder().user(user).build();
        authentication.setAuthenticated(true);
        ((JwtAuthentication) authentication).setUserDetails(userDetails);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
