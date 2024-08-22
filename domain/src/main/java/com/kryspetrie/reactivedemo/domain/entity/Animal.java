package com.kryspetrie.reactivedemo.domain.entity;

import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record Animal(
        @Nonnull UUID id,
        @Nonnull LocalDate updated,
        @Nonnull String name,
        @Nonnull String description,
        @Nonnull String conservationStatus) {
}
