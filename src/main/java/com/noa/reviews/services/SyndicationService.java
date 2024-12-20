package com.noa.reviews.services;

import com.noa.reviews.repositories.*;
import com.noa.reviews.entities.Store;
import com.noa.reviews.entities.Syndication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SyndicationService {
    private final SyndicationRepository syndicationRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public SyndicationService(SyndicationRepository syndicationRepository, StoreRepository storeRepository) {
        this.syndicationRepository = syndicationRepository;
        this.storeRepository = storeRepository;
    }

    // Creating a syndication relationship between two stores.
    public Boolean createSyndication(Long sourceStoreId, Long targetStoreId) {
        // Fetch source and target stores (Ensure both exist)
        Optional<Store> sourceStore = storeRepository.findById(sourceStoreId);
        Optional<Store> targetStore = storeRepository.findById(targetStoreId);

        if (sourceStore.isEmpty() || targetStore.isEmpty()) {
            throw new RuntimeException("Source or Target store not found.");
        }

        // Check if both stores belong to the same organization
        if (!sourceStore.get().getOrganizationId().equals(targetStore.get().getOrganizationId())) {
            throw new IllegalArgumentException("Stores must belong to the same organization for syndication.");
        }

        // Check if the syndication relationship already exists
        if (syndicationRepository.findBySourceStoreIdAndTargetStoreId(sourceStoreId, targetStoreId).isPresent()) {
            throw new IllegalArgumentException("Syndication relationship already exists.");
        }

        // Save the new syndication
        Syndication syndication = Syndication.builder()
                .sourceStoreId(sourceStoreId)
                .targetStoreId(targetStoreId)
                .build();
        syndicationRepository.save(syndication);
        return true;
    }
}
