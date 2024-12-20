package com.noa.reviews.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a product review in the system.
 */
@Entity // Marks this class as a JPA entity mapped to a table.
@Table(name = "review") // Maps this entity to the "reviews" table in the database.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review {

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value.
    private Long id;

    @Column(nullable = false, length = 100) // Maps the "content" field to a column. It cannot be null, and its length is limited to 100 characters.
    private String content;

    @Column(nullable = false) // Maps the "rating" field to a column. It cannot be null.
    private Integer rating;

    @Column(nullable = false) // Maps the "storeId" field to a column representing the ID of the store.
    private Long storeId;

    @Column(nullable = false) // Maps the "productId" field to a column representing the ID of the product.
    private Long productId;

    @Column(nullable = false) // Maps the "requestTime" field to a column representing when the review was created.
    private LocalDateTime requestTime;
}