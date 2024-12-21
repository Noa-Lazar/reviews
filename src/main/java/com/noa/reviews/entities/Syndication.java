package com.noa.reviews.entities;

import jakarta.persistence.*;
import lombok.*;

/**
 * Syndication Entity
 * Represents syndication relationships between stores.
 * A syndication links one store (source) to another store (target),
 * enabling reviews to be visible across stores.
 */

@Entity // Marks this class as a JPA entity mapped to a table.
@Table(name = "syndication") // Maps this entity to the "syndications" table in the database.
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Syndication {

    // The id of each syndication
    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value.
    private Long id;

    // The source store id
    @Column(nullable = false) // Maps the "sourceStoreId" field to a column. Can't be null
    private Long sourceStoreId;

    // The target store id
    @Column(nullable = false) // Maps the "targetStoreId" field to a column. Can't be null
    private Long targetStoreId;

}
