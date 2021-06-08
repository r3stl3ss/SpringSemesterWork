package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;

import java.nio.file.AccessDeniedException;

public interface SignInService {
    public TokenDto signIn(AuthForm authForm) throws AccessDeniedException;
}
