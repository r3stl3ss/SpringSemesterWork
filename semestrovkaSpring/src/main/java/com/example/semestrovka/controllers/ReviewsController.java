package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.UserDto;
import com.example.semestrovka.dto.forms.SignUpForm;
import com.example.semestrovka.models.Review;
import com.example.semestrovka.services.interfaces.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewsController {
    @Autowired
    private ReviewsService reviewsService;
    //TODO: ну и остальные матоды написать по образу и подобию
    @PreAuthorize("permitAll()")
    @GetMapping("/reviews")
    @ResponseBody
    public List<Review> getAllReviews() {
        return reviewsService.getAllReviews();
    }



}
