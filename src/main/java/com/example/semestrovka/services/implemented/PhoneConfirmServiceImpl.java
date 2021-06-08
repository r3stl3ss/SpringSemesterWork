package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.CodeInsertForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.UsersRepository;
import com.example.semestrovka.services.interfaces.PhoneConfirmService;
import com.example.semestrovka.services.interfaces.SmsSendService;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneConfirmServiceImpl implements PhoneConfirmService {
    @Autowired
    private SmsSendService smsSendService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public String generateConfirmationCode() {
        return String.valueOf((int)((Math.random()*90000)+10000));
    }

    @Override
    public void sendSmsWithCode(UserDto userDto) {
        String code = generateConfirmationCode();
        System.out.println(code); //debug
        User user = usersService.getUserByEmail(userDto.getEmail());
        user.setCurrentPhoneConfirmCode(code);
        user.setPhone(userDto.getPhone());
        usersRepository.save(user);
        smsSendService.sendSms(userDto.getPhone(), code);
    }

    public boolean verifyPhone(CodeInsertForm codeInsertForm, UserDto userDto) {
        User user = usersService.getUserByEmail(userDto.getEmail());
        if (codeInsertForm.getCode().equals(user.getCurrentPhoneConfirmCode())) {
            user.setCurrentPhoneConfirmCode(null);
            user.setPhoneProved(true);
            usersRepository.save(user);
            return true;
        }
        return false;
    }
}
