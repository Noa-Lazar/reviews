package com.noa.reviews.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateReviewRequest {

    @NotNull(message = "Review ID is required")
    private Long reviewId;

    @NotBlank(message = "Content cannot be empty")
    @Size(max = 100, message = "Content must be at most 100 characters")
    private String content;

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    @NotNull(message = "Request time is required")
    private LocalDateTime requestTime;

    // Getters and Setters
}

