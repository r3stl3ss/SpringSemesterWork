package com.example.semestrovka.dto.forms;

import com.example.semestrovka.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileUsernameEditForm {
    @NotNull
    private String username;

    @ValidPassword(message = "{errors.incorrect.password}")
    private String password;
}