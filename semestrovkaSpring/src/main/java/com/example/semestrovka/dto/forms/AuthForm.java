package com.example.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthForm {
    //@NotBlank(message = "email is mandatory")
    private String email;
    //@NotBlank(message = "password is mandatory")
    private String password;
}
