package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.ReviewDto;
import com.example.semestrovka.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ReviewService {
    Page<Review> getAllReviews(Pageable pageable);

    Page<Review> getAllReviewsByHeader(String header, Pageable pageable);

    void publishReview(ReviewDto reviewDto);

    public Review getReviewById(Long id);

    void publishReviewWithPicture(ReviewDto reviewDto, MultipartFile file) throws IOException;
}
