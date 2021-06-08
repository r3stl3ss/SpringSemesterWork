package com.example.semestrovka.dto;

import com.example.semestrovka.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
    private List<ReviewDto> listOfReviews;
    private int countOfSubscriptions;
    private int countOfSubscribers;
    public static UserDto fromUser(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .username(user.getUsername())
                .phone(user.getPhone())
                .isPhoneConfirmed(user.isPhoneProved())
                .imagePath(user.getPathToAvatar())
                .listOfReviews(user.getReviews().stream().map(ReviewDto::fromReview).collect(Collectors.toList()))
                .countOfSubscribers(user.getSubscribers().size())
                .countOfSubscriptions(user.getSubscriptions().size())
                .build();
    }

}
