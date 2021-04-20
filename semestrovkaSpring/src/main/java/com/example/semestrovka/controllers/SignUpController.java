package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.exceptions.ActivationCodeDoesNotExistException;
import com.example.semestrovka.services.interfaces.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        if (br.hasErrors()) {
            //TODO:remove debug
            System.out.println("беды с формой регистрации");
            model.addAttribute("signUpForm", "Проблема с формой, уберите это сообщение при деплое");
            return "signUpPage";
        }
        if (!signUpService.signUp(suf)) {
            //TODO:remove debug
            System.out.println("пользователь уже есть, нах ты дважды регаешься");
            model.addAttribute("signUpForm", "Пользователь уже существует");
            return "signUpPage";
        }
        //TODO:remove debug
        System.out.println(suf);
        System.out.println("Залогинен успешно");
        return "redirect:/signIn";
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) throws ActivationCodeDoesNotExistException {
        boolean isActivated = signUpService.activateUser(code);
        if (isActivated) {
            System.out.println("Activation successful");
            return "signInPage";
        }
        System.out.println("Activation is not successful");
        return "signUpPage";
    }
}