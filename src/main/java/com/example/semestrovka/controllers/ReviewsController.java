package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.ReviewDto;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.ReviewService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ReviewsController {
    @Autowired
    private ReviewService reviewsService;
    //TODO: ну и остальные матоды написать по образу и подобию
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/reviews")
    @ApiOperation(value = "Get all reviews")
    public ResponseEntity<List<ReviewDto>> getAllReviews() {
        return ResponseEntity.ok(reviewsService.getAllReviews().stream()
                .map(ReviewDto::fromReview)
                .collect(Collectors.toList()));
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reviewsWithPicture")
    @ApiOperation(value = "Publish a review with picture")
    public ResponseEntity<ReviewDto> publishReviewWithPicture(@RequestParam @NotNull String text,
                                                   @RequestParam @NotNull String header,
                                                   @RequestParam @NotNull double rate,
                                                   @RequestParam("file") MultipartFile file) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getDetails();
        ReviewDto reviewDto = ReviewDto.builder()
                .header(header)
                .timeOfCreation(LocalDateTime.now())
                .rate(rate)
                .nickOfCreator(currentUser.getUsername())
                .text(text)
                .build();
        if (file.getSize() > 0) {
            reviewsService.publishReviewWithPicture(reviewDto, file);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(reviewDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/reviews")
    @ApiOperation(value = "Publish a review without picture")
    public ResponseEntity<ReviewDto> publishReviewWithoutPicture(@RequestParam @NotNull String text,
                                                                 @RequestParam @NotNull String header,
                                                                 @RequestParam @NotNull double rate) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getDetails();
        ReviewDto reviewDto = ReviewDto.builder()
                .header(header)
                .timeOfCreation(LocalDateTime.now())
                .rate(rate)
                .nickOfCreator(currentUser.getUsername())
                .text(text)
                .build();
        reviewsService.publishReview(reviewDto);
        return ResponseEntity.ok(reviewDto);
    }

}
