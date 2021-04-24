package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 10000, nullable=false, updatable=false)
    private String text;

    //TODO: стринг сендер заменить на юзер сендер, но это когда настрою секьюрити и смогу в сообщение кидать ник пользователя
    @Column(nullable=false, updatable=false)
    private String sender;

    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;

}
