package com.example.semestrovka.controllers;

import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubscriptionController {

    @Autowired
    private UsersService usersService;

    @ApiOperation(value = "Подписка на пользователя")
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/user/subscribe/{userId}")
    public ResponseEntity<String> subscribe(
            @RequestBody @PathVariable Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getDetails();
        usersService.subscribe(currentUser.getUsername(), userId);
        return ResponseEntity.ok("Успешно подписан");
    }

    @ApiOperation(value = "Отписка от пользователя")
    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/user/unsubscribe/{userId}")
    public ResponseEntity<String> unsubscribe(
            @PathVariable Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getDetails();
        usersService.unsubscribe(currentUser.getUsername(), userId);
        return ResponseEntity.ok("Успешно отписан");
    }
}
