package com.example.semestrovka.dto;

import com.example.semestrovka.models.Message;
import lombok.Data;

@Data
public class MessageDto {
    private String text;

    private String sender;

    public static Message fromDto(MessageDto mdto) {
        return Message.builder()
                .text(mdto.getText())
                .sender(mdto.getSender())
                .build();
    }

}
