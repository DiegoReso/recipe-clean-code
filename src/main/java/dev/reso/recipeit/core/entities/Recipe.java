package dev.reso.recipeit.core.entities;

import dev.reso.recipeit.core.enums.TypeRecipe;

import java.time.LocalDateTime;

public class Recipe {

    private Long id;
    private String name;
    private String description;
    private String instruction;
    private double preparationTimeMinutes;
    private double servings;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TypeRecipe type;
}
