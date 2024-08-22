package com.kryspetrie.reactivedemo.application.rest;

import com.kryspetrie.reactivedemo.application.dtos.AnimalDto;
import com.kryspetrie.reactivedemo.domain.entity.Animal;
import com.kryspetrie.reactivedemo.domain.ports.in.GetAnimalUseCase;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping(path = "/animal")
@RequiredArgsConstructor
public class AnimalController {

    private final GetAnimalUseCase getAnimalUseCase;

    private AnimalDto asAnimalDto(@Nonnull Animal animal) {
        return AnimalDto.builder()
                .id(animal.id())
                .name(animal.name())
                .description(animal.description())
                .conservationStatus(animal.conservationStatus())
                .updated(animal.updated())
                .build();
    }

    @GetMapping(path = "/{id}")
    public Mono<AnimalDto> getAnimalById(@Nonnull final UUID id) {
        return getAnimalUseCase.getAnimalById(id)
                .map(this::asAnimalDto);
    }

    private static Pageable toPagable(int page, int size) {
        if (size == 0) {
            return Pageable.unpaged();
        }

        return PageRequest.of(page, size);
    }

    @GetMapping("/paged")
    public Mono<Page<AnimalDto>> getAnimalsPaged(
            @RequestParam(value = "page", defaultValue = "0", required = false) final int page,
            @RequestParam(value = "size", defaultValue = "0", required = false) final int size) {
        final var pageable = toPagable(page, size);

        if (pageable.isUnpaged()) {
            return getAnimalUseCase.getAnimals(pageable)
                    .map(this::asAnimalDto)
                    .collectList()
                    .map(list -> new PageImpl<>(list, Pageable.ofSize(list.size()), list.size()));
        }

        return getAnimalUseCase.getAnimals(pageable)
                .map(this::asAnimalDto)
                .collectList()
                .zipWith(getAnimalUseCase.countAnimals())
                .map(zipped -> new PageImpl<>(zipped.getT1(), pageable, zipped.getT2()));
    }

    @GetMapping(produces = {"application/json", "application/x-ndjson"})
    public Flux<AnimalDto> getAnimalsPagedFlux(
            @RequestParam(value = "page", defaultValue = "0", required = false) final int page,
            @RequestParam(value = "size", defaultValue = "0", required = false) final int size) {
        final var pageable = toPagable(page, size);
        return getAnimalUseCase.getAnimals(pageable)
                .map(this::asAnimalDto);
    }
}
