package dev.reso.recipeit.infra.dtos;

import dev.reso.recipeit.core.enums.TypeRecipe;

import java.time.LocalDateTime;

public record RecipeDto(
        Long id,
        String name,
        String description,
        String identification,
        String instruction,
        double preparationTimeMinutes,
        double servings,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        TypeRecipe type
) {}
