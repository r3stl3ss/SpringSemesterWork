package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.SignInService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {
    private UsersRepository ur;

    private PasswordEncoder pe;

    @Value("${jwt.secret}")
    private String secret;

    @SneakyThrows
    @Override
    public TokenDto signIn(AuthForm form) {
        Optional<User> signingIn = ur.findByEmail(form.getEmail());

        if (signingIn.isPresent()) {
            User user = signingIn.get();
            if (pe.matches(form.getPassword(), user.getHashPassword())) {
                String token = Jwts.builder()
                        .setSubject(String.valueOf(user.getId()))
                        .claim("name", user.getUsername())
                        .claim("role", user.getRole().name())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Неправильный пароль или логин");
        } else throw new AccessDeniedException("Нет такого пользователя");
    }
}
