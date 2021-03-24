package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.services.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage(Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        return "signUpPage";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm suf, BindingResult br, Model model) {
        if (!br.hasErrors()) {
            System.out.println(suf);
            signUpService.signUp(suf);
            return "redirect:/signIn";
        } else {
            System.out.println("беды с формой");
            model.addAttribute("signUpForm", suf);
            return "signUpPage";
        }
    }
}