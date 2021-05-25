package com.example.semestrovka.dto.forms;

import com.example.semestrovka.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfilePasswordEditForm {

    private String oldPassword;

    @ValidPassword
    private String newPassword;

    private String repeatNewPassword;
}