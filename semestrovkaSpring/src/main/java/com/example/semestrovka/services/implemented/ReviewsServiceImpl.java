package com.example.semestrovka.services.implemented;

import com.example.semestrovka.models.Review;
import com.example.semestrovka.repositories.ReviewsRepository;
import com.example.semestrovka.services.interfaces.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Override
    public List<Review> getAllReviews() {
        return reviewsRepository.findAll();
    }

}
