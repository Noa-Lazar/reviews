package com.noa.reviews.services;

import com.noa.reviews.dtos.*;
import com.noa.reviews.entities.*;
import com.noa.reviews.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


/**
 * Review Service
 * Manages the core operations for creating, updating, deleting, and retrieving reviews
 * while interacting with the ReviewRepository.
 */

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;


    @Autowired
    // Constructor injection for the ReviewRepository and StoreRepository
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    // Create a new review
    public boolean createReview(CreateReviewRequest review) {
        // Add custom validation if needed
        Review reviewEntity = Review.builder()
                .content(review.getContent())
                .rating(review.getRating())
                .storeId(review.getStoreId())
                .productId(review.getProductId())
                .requestTime(review.getRequestTime())
                .build();
        reviewRepository.save(reviewEntity);
        return true;
    }

    // Update an existing review
    public boolean updateReview(Long reviewId, UpdateReviewRequest updatedReview) {
        Optional<Review> existingReview = reviewRepository.findById(reviewId);
        if (!existingReview.isPresent())
            throw new RuntimeException("Review was not found with ID: " + reviewId);
        Review review = existingReview.get();
        review.setContent(updatedReview.getContent());
        review.setRating(updatedReview.getRating());
        review.setRequestTime(updatedReview.getRequestTime());
        reviewRepository.save(review);
        return true;
    }

    // Delete a review by ID
    public boolean deleteReview(Long reviewId) {
        if (!reviewRepository.existsById(reviewId))
            throw new RuntimeException("Review was not found with ID: " + reviewId);
        reviewRepository.deleteById(reviewId);
        return true;
    }

    // Get all reviews for a specific store
    public List<String> getReviewsByStoreId(Long storeId) {
        // Fetch direct reviews for the store
        List<Review> directReviews = reviewRepository.findAllReviewsByStoreIdAndSyndications(storeId);
        return directReviews.stream().map(Review::getContent).toList();
    }

}

