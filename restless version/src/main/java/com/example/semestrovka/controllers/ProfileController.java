package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.security.details.UserDetailsImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        UserDto userDto = UserDto.fromUser(userDetails.getUser());
        model.addAttribute("username", userDto.getUsername());
        model.addAttribute("id", userDto.getId());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("name", userDto.getName());
        model.addAttribute("imagePath", userDto.getImagePath());
        return "profile_page";
    }
}
