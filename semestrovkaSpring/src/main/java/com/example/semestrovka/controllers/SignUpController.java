package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.services.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm suf) {
        System.out.println(suf);
        signUpService.signUp(suf);
        return "redirect:/signUp";
    }
}