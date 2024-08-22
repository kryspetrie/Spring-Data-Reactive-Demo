package com.kryspetrie.reactivedemo.infrastructure.repository;

import com.kryspetrie.reactivedemo.infrastructure.entity.AnimalInventoryEntity;
import com.kryspetrie.reactivedemo.infrastructure.entity.InventoryCountsProjection;
import jakarta.annotation.Nonnull;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface AnimalInventoryRepository extends
        ReactiveCrudRepository<AnimalInventoryEntity, UUID>,
        ReactiveSortingRepository<AnimalInventoryEntity, UUID> {

    Flux<AnimalInventoryEntity> findByAnimalId(@Nonnull final UUID animalId);

    Flux<AnimalInventoryEntity> findByZooId(@Nonnull final UUID zooId);

    @Query("SELECT * FROM animal_inventory i JOIN animal a ON i.animal_id = a.id WHERE name = :name")
    Flux<AnimalInventoryEntity> findByAnimalName(@Nonnull final String name);

    @Query("SELECT * FROM animal_inventory i JOIN zoo z ON i.zoo_id = z.id WHERE name = :name")
    Flux<AnimalInventoryEntity> findByZooName(@Nonnull final String name);

    Flux<InventoryCountsProjection> findByZooIdAndAnimalId(@Nonnull UUID zooId, @Nonnull UUID animalId);
}
