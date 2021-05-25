package com.example.semestrovka.dto;

import com.example.semestrovka.dto.forms.AuthForm;
import com.example.semestrovka.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {
    private Long id;
    private String username;
    private String name;
    private String code;
    private String email;
    private String phone;
    private boolean isPhoneConfirmed;
    private String imagePath;
    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .phone(user.getPhone())
                .isPhoneConfirmed(user.isPhoneProved())
                .imagePath(user.getPathToAvatar())
                .build();
    }

}
