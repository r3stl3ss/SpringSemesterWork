package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;
import com.example.semestrovka.services.interfaces.SignInService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignInController {

    private SignInService sis;

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(@RequestBody AuthForm authData) {
        return ResponseEntity.ok(sis.signIn(authData));
    }
}
