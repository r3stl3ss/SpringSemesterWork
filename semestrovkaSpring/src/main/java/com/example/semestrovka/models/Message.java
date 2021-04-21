package com.example.semestrovka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="chat_messages")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
//TODO: переделать, от кого сообщение, и прицепить таймстамп
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(length = 10000, nullable=false)
    private String text;
    
    private String sender;
}
