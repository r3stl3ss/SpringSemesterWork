package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;

public interface SignInService {
    TokenDto signIn(AuthForm form);
}
