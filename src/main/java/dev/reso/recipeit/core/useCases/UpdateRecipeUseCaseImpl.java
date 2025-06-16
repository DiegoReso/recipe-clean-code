package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.InvalidArgumentException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import jakarta.persistence.EntityNotFoundException;

public class UpdateRecipeUseCaseImpl implements UpdateRecipeUseCase{

    private final RecipeGateway recipeGateway;

    public UpdateRecipeUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(Recipe recipe, Long id) throws InvalidArgumentException, ResourceNotFoundException  {
        if (id == null) {
            throw new InvalidArgumentException("Recipe ID cannot be null.");
        }
        if (id <= 0) {
            throw new InvalidArgumentException("Recipe ID must be a positive value. Received: " + id);
        }
        if (recipe == null) {
            throw new InvalidArgumentException("Recipe cannot be null.");
        }
        if (!recipe.getId().equals(id)) {
            throw new InvalidArgumentException("Recipe ID does not match the provided ID.");
        }
        if (recipe.getIdentification() == null || recipe.getIdentification().isBlank()) {
            throw new InvalidArgumentException("Recipe identification cannot be null or empty.");
        }
        if (recipe.getName() == null || recipe.getName().isBlank()) {
            throw new InvalidArgumentException("Recipe name cannot be null or empty.");
        }
        return recipeGateway.updateRecipe(recipe, id);
    }
}
