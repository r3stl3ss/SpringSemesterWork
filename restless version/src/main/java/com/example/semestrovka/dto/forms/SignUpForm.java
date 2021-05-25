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
public class SignUpForm {
    @NotBlank(message = "name is mandatory, no anonimity")
    private String name;
    @NotBlank(message = "username is mandatory")
    private String username;
    @Email(message = "{errors.incorrect.email}")
    private String email;
    @NotBlank(message = "password is mandatory")
    @ValidPassword(message = "{errors.incorrect.password}")
    private String password;
}
