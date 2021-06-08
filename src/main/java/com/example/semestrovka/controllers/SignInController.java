package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.TokenDto;
import com.example.semestrovka.dto.forms.AuthForm;
import com.example.semestrovka.services.interfaces.SignInService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RestController
public class SignInController {

    @Autowired
    private SignInService signInService;


    @PostMapping("/signIn")
    @ApiOperation(value = "Sign in(get JWT)")
    public ResponseEntity<TokenDto> signIn(AuthForm authData) {
        try {
            return ResponseEntity.ok(signInService.signIn(authData));
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}

