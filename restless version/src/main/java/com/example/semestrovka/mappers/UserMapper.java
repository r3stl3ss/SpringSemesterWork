package com.example.semestrovka.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserMapper {
    

    //@Mapping(target = "userId", source = "dto.id")
    //@Mapping(target = "userEmail", source = "dto.email")
    //@Mapping(target = "userUsername", source = "dto.username")
   // User userDtoToUser(UserDto userDto);

   // @Mapping(target = "userId", source = "entity.id")
   // @Mapping(target = "userEmail", source = "entity.email")
   // @Mapping(target = "userUsername", source = "entity.username")
   // UserDto userToUserDto(User user);
}


