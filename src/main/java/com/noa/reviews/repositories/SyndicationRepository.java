package com.noa.reviews.repositories;

import com.noa.reviews.entities.Syndication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SyndicationRepository extends JpaRepository<Syndication, Long> {

    // Find connection
    Optional<Syndication> findBySourceStoreIdAndTargetStoreId(Long sourceStoreId, Long targetStoreId);

    // Find all syndications where the source store matches
    List<Syndication> findBySourceStoreId(Long sourceStoreId);

    // Find all syndications where the target store matches
    List<Syndication> findByTargetStoreId(Long targetStoreId);
}
