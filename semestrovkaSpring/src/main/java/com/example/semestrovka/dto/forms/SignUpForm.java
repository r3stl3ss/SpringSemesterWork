package com.example.semestrovka.dto.forms;

import com.example.semestrovka.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
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
    //@NotBlank(message = "email is mandatory") чтобы две ошибки не кидал
    @Email(message = "{errors.incorrect.email}")
    private String email;
    //@NotBlank(message = "password is mandatory")
    @ValidPassword(message = "{errors.incorrect.password}")
    private String password;
}
