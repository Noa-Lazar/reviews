package com.noa.reviews.repositories;

import com.noa.reviews.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Custom method to find all reviews for a specific store
    List<Review> findByStoreId(Long storeId);

    @Query(value = """
            SELECT r.*
            FROM review r
            LEFT JOIN syndication s ON r.store_id = s.source_store_id
            WHERE r.store_id = :storeId OR s.target_store_id = :storeId
        """, nativeQuery = true)
    List<Review> findAllReviewsByStoreIdAndSyndications(@Param("storeId") Long storeId);
}
