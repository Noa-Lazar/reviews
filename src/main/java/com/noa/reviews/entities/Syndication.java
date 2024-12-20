package com.noa.reviews.entities;

import jakarta.persistence.*;
import lombok.*;

/**
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

    @Id // Marks this field as the primary key.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value.
    private Long id;

    @Column(nullable = false) // Maps the "sourceStoreId" field to a column. Represents the source store in the relationship.
    private Long sourceStoreId;

    @Column(nullable = false) // Maps the "targetStoreId" field to a column. Represents the target store in the relationship.
    private Long targetStoreId;
}
