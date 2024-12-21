package com.noa.reviews.controller;

import com.noa.reviews.dtos.CreateReviewRequest;
import com.noa.reviews.dtos.UpdateReviewRequest;
import com.noa.reviews.services.ReviewService;
import com.noa.reviews.services.SyndicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * System Controller
 * Handles HTTP requests related to reviews and syndications, providing endpoints for creating,
 * updating, deleting reviews, retrieving reviews by store, and establishing syndication
 * relationships between stores.
 */
@RestController
@RequestMapping("/reviews")
public class SystemController {

    private final ReviewService reviewService;

    private final SyndicationService syndicationService;

    // The ReviewController depends on ReviewService. So we do constructor injection instead of creating it.
    @Autowired
    public SystemController(ReviewService reviewService,
                            SyndicationService syndicationService1) {
        this.reviewService = reviewService;
        this.syndicationService = syndicationService1;
    }

    // Create a Review (POST)
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody CreateReviewRequest review) {
        reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body("Review created successfully");
    }

    // Update a Review (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id, @RequestBody UpdateReviewRequest review) {
        reviewService.updateReview(id, review);
        return ResponseEntity.status(HttpStatus.OK).body("Review updated successfully");
    }

    // Delete a Review (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.status(HttpStatus.OK).body("Review deleted successfully");
    }

    // Get all reviews for a Store (GET)
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<String>> getReviewsByStore(@PathVariable Long storeId) {
        List<String> reviews = reviewService.getReviewsByStoreId(storeId);
        return ResponseEntity.ok(reviews);
    }

    // Create syndication between twi stores
    @PostMapping("/store/{storeId}/syndication")
    public ResponseEntity<Void> createSyndication(@PathVariable Long storeId, @RequestBody Long targetStoreId) {
        syndicationService.createSyndication(storeId, targetStoreId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
