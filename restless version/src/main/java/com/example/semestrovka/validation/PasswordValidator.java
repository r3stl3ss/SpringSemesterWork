package com.example.semestrovka.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return  value.length() > 8 &&
                value.matches("^[A-Za-z0-9]{9}$");
    }
}
