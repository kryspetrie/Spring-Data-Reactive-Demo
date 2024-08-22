package com.kryspetrie.reactivedemo.application.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record AnimalDto(
        @Nonnull UUID id,
        @JsonFormat(pattern="yyyy-MM-dd")
        @Nonnull LocalDate updated,
        @Nonnull String name,
        @Nonnull String description,
        @Nonnull String conservationStatus) {
}
