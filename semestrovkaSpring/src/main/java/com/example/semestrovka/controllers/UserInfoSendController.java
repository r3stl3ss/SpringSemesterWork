package com.example.semestrovka.controllers;

import com.example.semestrovka.models.User;
import com.example.semestrovka.services.interfaces.SendMailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserInfoSendController {
    private SendMailService sender;
    private PasswordEncoder pe;

    //я знаю, что так не должно быть, но иначе я не понимаю, куда ещё навешивать преАвторайз
    @PreAuthorize("#user.createdOfDetails == true")
    public void sendFullInfo(User user) {
        String message = "Почта: " + user.getEmail() + "\n" +
                "Номер телефона: " + user.getPhone() + "\n" +
                "Никнейм: " + user.getUsername() + "\n" +
                "Инфо о себе: " + user.getAbout() + "\n" +
                "Пароль: " + pe.encode(user.getHashPassword());
        sender.send(user.getEmail(), "info about you", message);
    }
}
