package com.example.semestrovka.handlers;

import com.example.semestrovka.dto.MessageDto;
import com.example.semestrovka.repositories.MessagesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
public class WebSocketMessageHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MessagesRepository mr;

    private static final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(message.getPayload());
        MessageDto messageFromJSON = om.readValue(message.getPayload(), MessageDto.class);

        if (!sessions.containsKey(messageFromJSON.getSender())) {
            sessions.put(messageFromJSON.getSender(), session);
        }

        mr.save(MessageDto.fromDto(messageFromJSON));

        for (WebSocketSession curSession : sessions.values()) {
            curSession.sendMessage(message);
        }
    }
}
