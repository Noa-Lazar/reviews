package com.noa.reviews.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 * Review Entity
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

    // Review id
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private Long id;

    // Review content
    @Column(nullable = false) // Maps the "content" field to a column. Can't be null
    private String content;

    // Review content
    @Column(nullable = false) // Maps the "rating" field to a column. Can't be null
    private Integer rating;

    // Review store id
    @Column(nullable = false) // Maps the "storeId" field to a column. Can't be null
    private Long storeId;

    // Review product id
    @Column(nullable = false) // Maps the "productId" field to a column. Can't be null
    private Long productId;

    // Review request time
    @Column(nullable = false) // Maps the "requestTime" field to a column. Can't be null
    private LocalDateTime requestTime;

}