package dev.reso.recipeit.core.entities;

import dev.reso.recipeit.core.enums.TypeRecipe;

import java.time.LocalDateTime;

public class Recipe {

    private Long id;
    private String name;
    private String description;
    private String identification;
    private String instruction;
    private double preparationTimeMinutes;
    private double servings;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TypeRecipe type;

    public Recipe() {
    }

    public Recipe(Long id, String name, String description, String identification, String instruction, double servings, double preparationTimeMinutes, LocalDateTime createdAt, LocalDateTime updatedAt, TypeRecipe type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.identification = identification;
        this.instruction = instruction;
        this.preparationTimeMinutes = preparationTimeMinutes;
        this.servings = servings;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public double getPreparationTimeMinutes() {
        return preparationTimeMinutes;
    }

    public void setPreparationTimeMinutes(double preparationTimeMinutes) {
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public double getServings() {
        return servings;
    }

    public void setServings(double servings) {
        this.servings = servings;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public TypeRecipe getType() {
        return type;
    }

    public void setType(TypeRecipe type) {
        this.type = type;
    }


}
