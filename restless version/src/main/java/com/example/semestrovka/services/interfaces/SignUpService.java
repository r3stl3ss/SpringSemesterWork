package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.exceptions.ActivationCodeDoesNotExistException;

public interface SignUpService {
    boolean signUp(SignUpForm form);

    boolean activateUser(String code) throws ActivationCodeDoesNotExistException;
}

