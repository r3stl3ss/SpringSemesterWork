package com.example.semestrovka.services.interfaces;


import com.example.semestrovka.dto.SmsInfo;

public interface SmsSendService {
    String sendSms(String phone, String text);

    String checkSmsStatus(SmsInfo smsInfo);
}