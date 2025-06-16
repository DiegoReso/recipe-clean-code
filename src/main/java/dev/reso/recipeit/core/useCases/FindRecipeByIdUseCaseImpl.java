package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.InvalidArgumentException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;

public class FindRecipeByIdUseCaseImpl implements FindRecipeByIdUseCase {

    private final RecipeGateway recipeGateway;

    public FindRecipeByIdUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(Long id) throws InvalidArgumentException, ResourceNotFoundException {

        if (id == null) {
            throw new InvalidArgumentException("Recipe ID cannot be null.");
        }
        if (id <= 0) {
            throw new InvalidArgumentException("Recipe ID must be a positive value. Received: " + id);
        }

        return recipeGateway.findRecipeById(id);
    }
}
