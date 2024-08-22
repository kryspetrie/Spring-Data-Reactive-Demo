package com.kryspetrie.reactivedemo.domain.ports.in;

import com.kryspetrie.reactivedemo.domain.entity.Animal;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface GetAnimalUseCase {

    Mono<Animal> getAnimalById(@Nonnull final UUID id);

    Flux<Animal> getAnimals(@Nonnull final Pageable pageable);

    Mono<Long> countAnimals();
}
