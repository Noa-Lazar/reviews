package com.noa.reviews.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Store Entity
 * Represents a store in the system.
 */

@Entity // Marks this class as a JPA entity mapped to a table
@Table(name = "store") // Maps this entity to the "stores" table in the database
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    // The store id
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private Long id;

    // The store organization
    @Column(nullable = false) // Maps the "organizationId" field to a column. Can't be null
    private Long organizationId;

    // The store name
    @Column(nullable = false) // Maps the "storeName" field to a column. Can't be null
    private String storeName;

}
