package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.CodeInsertForm;
import com.example.semestrovka.dto.forms.PhoneConfirmForm;
import com.example.semestrovka.dto.forms.ProfileUsernameEditForm;
import com.example.semestrovka.dto.forms.ProfilePasswordEditForm;
import com.example.semestrovka.models.User;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.EditUserService;
import com.example.semestrovka.services.interfaces.PhoneConfirmService;
import com.example.semestrovka.services.interfaces.SmsSendService;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@PreAuthorize("isAuthenticated()")
@Controller
@RequiredArgsConstructor
public class EditProfileController {
    private final UsersService usersService;
    private final EditUserService editUserService;
    private final PhoneConfirmService phoneConfirmService;

    @GetMapping("/editProfile")
    public String getEditProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                     Model model) {
        UserDto userDto = UserDto.fromUser(userDetails.getUser());
        model.addAttribute("userName", userDto.getName());
        model.addAttribute("nameEditForm", new ProfileUsernameEditForm());
        model.addAttribute("passwordEditForm", new ProfilePasswordEditForm());

        return "edit";
    }

    @GetMapping("/editProfile/confirmPhone")
    public String getConfirmationPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                      Model model) {
        model.addAttribute("phoneConfirmForm", new PhoneConfirmForm());
        return "confirmation";
    }

    @PostMapping("/editProfile/password")
    public String editProfilePassword(@Valid ProfilePasswordEditForm passwordEditForm,
                                      BindingResult bindingResult,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails,
                                      Model model) {
        if (!bindingResult.hasErrors()) {
            if (editUserService.editPassword(passwordEditForm,
                    UserDto.fromUser(userDetails.getUser()))) {
                model.addAttribute("message", "password successfully updated!");
            } else {
                model.addAttribute("message", "password is incorrect!");
            }
        }
        return "redirect:/editProfile";
    }

    @PostMapping("/editProfile/username")
    public String editProfileName(@Valid ProfileUsernameEditForm nameEditForm,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails,
                                  Model model) {
        boolean exists = usersService.existsByUsername(nameEditForm.getUsername());
        if (!exists) {
            if (editUserService.editUsername(nameEditForm,
                    UserDto.fromUser(userDetails.getUser()))) {
                model.addAttribute("message", "profile successfully updated!");
            } else {
                model.addAttribute("message", "password is incorrect!");
            }
        }
        return "redirect:/editProfile";
    }

    @PostMapping("/editProfile/confirmPhone/confirm")
    public String getConfirmationCodePage(@Valid PhoneConfirmForm phoneConfirmForm,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails,
                                          Model model) {
        boolean exists = usersService.existsByPhone(phoneConfirmForm.getPhone());

        if (exists) {
            model.addAttribute("message", "User with this phone number already exists");
            return "redirect:/editProfile/confirmPhone";
        } else {
            UserDto userDto = UserDto.fromUser(userDetails.getUser());
            userDto.setPhone(phoneConfirmForm.getPhone());
            phoneConfirmService.sendSmsWithCode(userDto);
            return "redirect:/editProfile/confirmPhone/confirm/code";
        }
    }

    @GetMapping("/editProfile/confirmPhone/confirm/code")
    public String getCodeInsertPage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                    Model model) {
        model.addAttribute("codeInsertForm", new CodeInsertForm());
        return "enterCode";
    }

    @PostMapping("/editProfile/confirmPhone/confirm/code")
    public String confirmPhone(@Valid CodeInsertForm codeInsertForm,
                               @AuthenticationPrincipal UserDetailsImpl userDetails,
                               Model model) {
        UserDto userDto = UserDto.fromUser(userDetails.getUser());
        boolean confirmed = phoneConfirmService.verifyPhone(codeInsertForm, userDto);
        if (confirmed) {
            model.addAttribute("success_confirm_message", "Phone confirmed!");
            return "redirect:/editProfile";
        } else {
            model.addAttribute("fail_confirm_message", "Wrong confirmation code");
            return "redirect:/editProfile/confirmPhone/confirm/code";
        }
    }

    @PostMapping("/editProfile/image")
    public String editImage(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            @RequestParam("file") MultipartFile file,
                            Model model) throws IOException {
        User user = usersService.getUserById(userDetails.getUser().getId());
        UserDto userDto =  UserDto.fromUser(user);
        editUserService.editImage(userDto, file);
        model.addAttribute("message", "Your avatar was successfully updated!");
        return "redirect:/profile";
    }
}
