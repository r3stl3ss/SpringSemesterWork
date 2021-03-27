package com.example.semestrovka.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class SignUpForm extends AuthForm {
    @NotBlank(message = "name is mandatory, no anonimity")
    private String name;
    @NotBlank(message = "username is mandatory")
    private String username;
    @NotBlank(message = "email is mandatory")
    private String email;
    @NotBlank(message = "password is mandatory")
    private String password;
}
