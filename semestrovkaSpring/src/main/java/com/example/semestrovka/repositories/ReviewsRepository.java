package com.example.semestrovka.repositories;

import com.example.semestrovka.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
}
