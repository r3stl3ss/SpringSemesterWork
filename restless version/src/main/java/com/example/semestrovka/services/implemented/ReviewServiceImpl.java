package com.example.semestrovka.services.implemented;

import com.example.semestrovka.dto.ReviewDto;
import com.example.semestrovka.models.Review;
import com.example.semestrovka.models.User;
import com.example.semestrovka.repositories.ReviewsRepository;
import com.example.semestrovka.services.interfaces.FileService;
import com.example.semestrovka.services.interfaces.ReviewService;
import com.example.semestrovka.services.interfaces.SendMailService;
import com.example.semestrovka.services.interfaces.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private FileService fileService;

    @Autowired
    private SendMailService sendMailService;

    @Override
    public Page<Review> getAllReviews(Pageable pageable) {
        return reviewsRepository.findAll(pageable);
    }

    @Override
    public Page<Review> getAllReviewsByHeader(String header, Pageable pageable) {
        return reviewsRepository.findByHeader(header, pageable);
    }

    @Override
    public void publishReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                    .author(usersService.findByUsername(reviewDto.getNickOfCreator()))
                    .createdAt(reviewDto.getTimeOfCreation())
                    .rating(reviewDto.getRate())
                    .text(reviewDto.getText())
                    .header(reviewDto.getHeader())
                    .build();
        reviewsRepository.save(review);
        for (User sub: review.getAuthor().getSubscribers()) {
            sendMailService.send(sub.getEmail(), "?????????? ??????????", "Hello, " +
                    sub.getUsername() + "\n" +
                    "???????????????????????? " + review.getAuthor().getUsername() +
                    " ?????????????????????? ?????????? ?????????? - ???????????????? ???? ?????? ????????????????: \n" +
                    "http://localhost/reviews/" + review.getAuthor().getId());
        }
    }

    @Override
    public void publishReviewWithPicture(ReviewDto reviewDto, MultipartFile file) throws IOException {
        String resultFileName = fileService.createFile(uploadPath, file);
        Review review = Review.builder()
                .header(reviewDto.getHeader())
                .text(reviewDto.getText())
                .rating(reviewDto.getRate())
                .author(usersService.findByUsername(reviewDto.getNickOfCreator()))
                .createdAt(reviewDto.getTimeOfCreation())
                .pathToPicture(resultFileName)
                .build();
        for (User sub: review.getAuthor().getSubscribers()) {
            sendMailService.send(sub.getEmail(), "?????????? ??????????", "Hello, " +
                    sub.getUsername() + "\n" +
                    "???????????????????????? " + review.getAuthor().getUsername() +
                    " ?????????????????????? ?????????? ?????????? - ???????????????? ???? ?????? ????????????????: \n" +
                    "http://localhost/reviews/" + review.getAuthor().getId());
        }
        reviewsRepository.save(review);
    }


    public Review getReviewById(Long id) {
        return reviewsRepository.findById(id).get();
    }
}
