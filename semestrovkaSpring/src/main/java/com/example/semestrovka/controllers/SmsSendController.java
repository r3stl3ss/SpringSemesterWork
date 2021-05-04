package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.SmsInfo;
import com.example.semestrovka.services.interfaces.SmsConfirmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.PermitAll;

@Controller
@RequestMapping("/confirmPhone")
@RequiredArgsConstructor
public class SmsSendController {

    private final SmsConfirmService smsSender;

    @PermitAll
    @GetMapping("/sendSms")
    public String sendSmsMessage(@RequestParam String phone, @RequestParam String text) {
        return smsSender.sendSms(phone, text);
    }

    @PermitAll
    @GetMapping("/checkStatus")
    public String checkSmsStatus(@ModelAttribute SmsInfo smsInfo){
        return smsSender.checkSmsStatus(smsInfo);
    }
}