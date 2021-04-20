package com.example.semestrovka.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("isAuthenticated()")
public class ProfileController {
    @GetMapping("/profile")
    public String getProfilePage() {
        return "profile_page";
    }
}
