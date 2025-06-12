package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;

public class FindRecipeByIdUseCaseImpl implements FindRecipeByIdUseCase {

    private final RecipeGateway recipeGateway;

    public FindRecipeByIdUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(Long id) {

        if (id == null) {
            throw new ResourceNotFoundException("ID cannot be null");
        }
        if (!recipeGateway.existsRecipeId(id) || id < 0) {
            throw new ResourceNotFoundException("Recipe with ID '" + id + "' not found");
        }

        return recipeGateway.findRecipeById(id);
    }
}
