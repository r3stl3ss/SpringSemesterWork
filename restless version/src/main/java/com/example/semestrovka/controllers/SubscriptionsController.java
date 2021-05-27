package com.example.semestrovka.controllers;

import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class SubscriptionsController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/user/subscribe/{userId}")
    public String subscribe(
            @AuthenticationPrincipal UserDetails currentUser,
            @PathVariable Long userId) {
        usersService.subscribe(currentUser.getUsername(), userId);
        return "redirect:/reviews/{userId}";
    }

    @GetMapping("/user/unsubscribe/{userId}")
    public String unsubscribe(
            @AuthenticationPrincipal UserDetails currentUser,
            @PathVariable Long userId) {
        usersService.unsubscribe(currentUser.getUsername(), userId);
        return "redirect:/reviews/{userId}";
    }

    @GetMapping("/user/subs/{userId}")
    public String getSubscribersPage(
            @PathVariable Long userId,
            Model model) {
        model.addAttribute("username", usersService.findById(userId).getUsername());
        model.addAttribute("subscribers", usersService.findById(userId).getSubscribers());
        model.addAttribute("subscriptions", usersService.findById(userId).getSubscriptions());
        return "subs";
    }
}
