package com.kryspetrie.reactivedemo.infrastructure.adapter;

import com.kryspetrie.reactivedemo.domain.entity.Animal;
import com.kryspetrie.reactivedemo.domain.ports.out.GetAnimalPort;
import com.kryspetrie.reactivedemo.infrastructure.entity.AnimalEntity;
import com.kryspetrie.reactivedemo.infrastructure.repository.AnimalRepository;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AnimalAdapter implements GetAnimalPort {

    private final AnimalRepository animalRepository;

    private Animal toAnimal(@Nonnull final AnimalEntity entity) {
        return Animal.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .conservationStatus(entity.getConservationStatus())
                .updated(entity.getUpdated())
                .build();
    }

    @Override
    public Mono<Animal> getAnimalById(@Nonnull UUID id) {
        return animalRepository.findById(id)
                .map(this::toAnimal);
    }

    @Override
    public Flux<Animal> getAnimals(@Nonnull Pageable pageable) {
        if (pageable.isUnpaged()) {
            return animalRepository.findAll()
                    .map(this::toAnimal);
        }

        return animalRepository.findAllBy(pageable)
                .map(this::toAnimal);
    }

    @Override
    public Mono<Long> countAnimals() {
        return animalRepository.count();
    }
}
