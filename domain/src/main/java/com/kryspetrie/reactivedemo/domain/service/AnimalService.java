package com.kryspetrie.reactivedemo.domain.service;

import com.kryspetrie.reactivedemo.domain.ports.out.GetAnimalPort;
import com.kryspetrie.reactivedemo.domain.entity.Animal;
import com.kryspetrie.reactivedemo.domain.ports.in.GetAnimalUseCase;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AnimalService implements GetAnimalUseCase {

    final GetAnimalPort port;

    @Override
    public Mono<Animal> getAnimalById(@Nonnull UUID id) {
        return port.getAnimalById(id);
    }

    @Override
    public Flux<Animal> getAnimals(@Nonnull Pageable pageable) {
        return port.getAnimals(pageable);
    }

    @Override
    public Mono<Long> countAnimals() {
        return port.countAnimals();
    }
}
