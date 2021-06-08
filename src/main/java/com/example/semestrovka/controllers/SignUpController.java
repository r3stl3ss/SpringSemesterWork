package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.services.interfaces.SignUpService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/signUp")
    @ApiOperation(value = "Sign up")
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpForm signUpForm) {
        if (signUpService.signUp(signUpForm)) {
            return ResponseEntity.ok("Регистрация завершена, вы зарегистрированы");
        }
        return ResponseEntity.ok("Пользователь с такой почтой уже существует");
    }
}