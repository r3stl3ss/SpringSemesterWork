package com.example.semestrovka.services.interfaces;

public interface SendMailService {
    void send(String destinationEmail, String subject, String message);
}
