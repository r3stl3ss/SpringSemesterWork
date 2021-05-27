package com.example.semestrovka.repositories;

import com.example.semestrovka.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessagesRepository extends JpaRepository<Message, Long> {
}
