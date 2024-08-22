package com.kryspetrie.reactivedemo.infrastructure.repository;

import com.kryspetrie.reactivedemo.infrastructure.entity.AnimalEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface AnimalRepository extends
        ReactiveCrudRepository<AnimalEntity, UUID>,
        ReactiveSortingRepository<AnimalEntity, UUID> {

    Flux<AnimalEntity> findByName(@Nonnull final String name);

    Flux<AnimalEntity> findAllBy(@Nonnull Pageable pageable);
}
