package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.UsersService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.semestrovka.dto.UserDto.fromUser;


@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final UsersService viewUserService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    @ApiOperation("Get profile information")
    public ResponseEntity<UserDto> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getDetails();
        return ResponseEntity.ok(fromUser(viewUserService.findByUsername(currentUser.getUsername())));
    }
}
