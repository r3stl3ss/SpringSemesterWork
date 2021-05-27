package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.models.User;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    @Autowired
    private UsersService usersService;
    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        User user = usersService.getUserById(userDetails.getUser().getId());
        UserDto userDto = UserDto.fromUser(user);
        model.addAttribute("username", userDto.getUsername());
        model.addAttribute("id", userDto.getId());
        model.addAttribute("email", userDto.getEmail());
        model.addAttribute("name", userDto.getName());
        model.addAttribute("imagePath", userDto.getImagePath());
        model.addAttribute("phone", userDto.getPhone());
        return "profile_page";
    }
}
