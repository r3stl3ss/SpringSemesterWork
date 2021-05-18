package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;
import com.example.semestrovka.services.interfaces.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignInController {

    @Autowired
    private SignInService signInService;

    @GetMapping("/signIn")
    public String getSignIn() {
        return "signInPage";
    }

    @PostMapping("/signIn")
    public ResponseEntity<TokenDto> signIn(AuthForm authData) {
        return ResponseEntity.ok(signInService.signIn(authData));
    }
}
