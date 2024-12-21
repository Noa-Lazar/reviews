package com.noa.reviews.dtos;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Create Review Request DTO
 */

@Getter
@Setter
public class CreateReviewRequest {

    // Review content
    @NotBlank(message = "Content cannot be empty")
    @Size(max = 100, message = "Content must be at most 100 characters")
    private String content;

    // Review rating
    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private Integer rating;

    // Review store id
    @NotNull(message = "Store ID is required")
    private Long storeId;

    // Review product id
    @NotNull(message = "Product ID is required")
    private Long productId;

    // Review request time
    @NotNull(message = "Request time is required")
    private LocalDateTime requestTime;

}

