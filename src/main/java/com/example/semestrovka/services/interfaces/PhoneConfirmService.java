package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.CodeInsertForm;

public interface PhoneConfirmService {
    public String generateConfirmationCode();

    public void sendSmsWithCode(UserDto userDto);

    public boolean verifyPhone(CodeInsertForm codeInsertForm, UserDto userDto);
}
