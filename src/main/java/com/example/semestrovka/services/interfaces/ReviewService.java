package com.example.semestrovka.services.interfaces;

import com.example.semestrovka.dto.ReviewDto;
import com.example.semestrovka.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();

    Page<Review> getAllReviewsByHeader(String header, Pageable pageable);

    String publishReview(ReviewDto reviewDto);

    public Review getReviewById(Long id);

    String publishReviewWithPicture(ReviewDto reviewDto, MultipartFile file) throws IOException;
}
