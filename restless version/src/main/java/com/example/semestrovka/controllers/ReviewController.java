package com.example.semestrovka.controllers;

import com.example.semestrovka.dto.ReviewDto;
import com.example.semestrovka.models.Review;
import com.example.semestrovka.security.details.UserDetailsImpl;
import com.example.semestrovka.services.interfaces.ReviewService;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UsersService usersService;


    @GetMapping("/reviews")
    public String getReviewsPage(Model model,
                                 @RequestParam(required = false, defaultValue = "") String filter,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if ((filter != null) && !filter.isEmpty()) {
            Page<Review> reviewsOrderedByIdAndHeader = reviewService.getAllReviewsByHeader(filter, pageable);
            model.addAttribute("page", reviewsOrderedByIdAndHeader);
        } else {
            Page<Review> reviewsOrderedById = reviewService.getAllReviews(pageable);
            model.addAttribute("page", reviewsOrderedById);
        }
        model.addAttribute("url", "/reviews");
        model.addAttribute("filter", filter);
        return "reviews";
    }

    @PostMapping("/reviews")
    public String addReview(@AuthenticationPrincipal UserDetailsImpl userDetails,
                            Model model,
                            @RequestParam @NotNull String text,
                            @RequestParam @NotNull String header,
                            @RequestParam @NotNull double rate,
                            @RequestParam("file") MultipartFile file ) throws IOException {
        ReviewDto reviewDto = ReviewDto.builder()
                .header(header)
                .timeOfCreation(LocalDateTime.now())
                .rate(rate)
                .nickOfCreator(userDetails.getUsername())
                .text(text)
                .build();
        if (file.getSize() > 0) {
            reviewService.publishReviewWithPicture(reviewDto, file);
        } else {
            reviewService.publishReview(reviewDto);
        }
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{userId}")
    public String getUserReviews(
            @AuthenticationPrincipal UserDetails currentUserDetails,
            @PathVariable Long userId,
            Model model) {
        List<Review> reviews = (usersService.findById(userId).getReviews());
        Collections.reverse(reviews);
        model.addAttribute("userChannel", usersService.findById(userId));
        model.addAttribute("isSubscriber", usersService.findById(userId).getSubscribers().contains(usersService.findByUsername(currentUserDetails.getUsername())));
        model.addAttribute("currentUserUsername", currentUserDetails.getUsername());
        model.addAttribute("subscriptionsCount", usersService.findById(userId).getSubscriptions().size());
        model.addAttribute("subscribersCount", usersService.findById(userId).getSubscribers().size());
        model.addAttribute("reviews", reviews);
        return "userReviews";
    }
}
