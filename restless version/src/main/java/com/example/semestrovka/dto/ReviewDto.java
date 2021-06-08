package com.example.semestrovka.dto;

import com.example.semestrovka.models.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewDto {
    private Long id;
    private String text;
    private String header;
    private LocalDateTime timeOfCreation;
    private String nickOfCreator;
    private String pathToPicture;
    private double rate;
    public static ReviewDto fromReview(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .header(review.getHeader())
                .text(review.getText())
                .nickOfCreator(review.getAuthor().getUsername())
                .rate(review.getRating())
                .timeOfCreation(review.getCreatedAt())
                .pathToPicture(review.getPathToPicture())
                .build();
    }
}
