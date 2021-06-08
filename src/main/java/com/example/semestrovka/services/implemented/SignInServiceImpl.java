package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.SignInService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SignInServiceImpl implements SignInService {

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private final UsersRepository usersRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public TokenDto signIn(AuthForm authForm) throws AccessDeniedException {
        Optional<User> signingIn = usersRepository.findByUsername(authForm.getUsername());
        if (signingIn.isPresent()) {
            User user = signingIn.get();
            if (passwordEncoder.matches(authForm.getPassword(), user.getHashPassword())) {
                String token = Jwts.builder()
                        .setSubject(String.valueOf(user.getId()))
                        .claim("username", user.getUsername())
                        .claim("role", user.getRole().toString())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Ошибка в авторизационных данных");
        } else throw new AccessDeniedException("Пользователь с таким ником уже существует");
    }
}
