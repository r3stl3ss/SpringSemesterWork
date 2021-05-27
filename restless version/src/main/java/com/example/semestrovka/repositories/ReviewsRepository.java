package com.example.semestrovka.repositories;

import com.example.semestrovka.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewsRepository extends JpaRepository<Review, Long> {

    Page<Review> findByHeader(String header, Pageable pageable);

    Page<Review> findAll(Pageable pageable);
}
