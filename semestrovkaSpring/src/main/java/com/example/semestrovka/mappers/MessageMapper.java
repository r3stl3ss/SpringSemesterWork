package com.example.semestrovka.mappers;

import com.example.semestrovka.dto.MessageDto;
import com.example.semestrovka.models.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface MessageMapper {
    @Mapping(target = "messageText", source = "dto.text")
    @Mapping(target = "messageFrom", source = "dto.from")
    Message messageDtoToMessage(MessageDto messageDto);

    @Mapping(target = "messageText", source = "entity.text")
    @Mapping(target = "messageFrom", source = "entity.from")
    MessageDto messageToMessageDto(Message message);
}
