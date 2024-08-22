package com.kryspetrie.reactivedemo.infrastructure.repository;

import com.kryspetrie.reactivedemo.infrastructure.entity.ZooEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface ZooRepository extends
        ReactiveCrudRepository<ZooEntity, UUID>,
        ReactiveSortingRepository<ZooEntity, UUID> {

    Flux<ZooEntity> findByName(@Nonnull final String name);
}
