package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import jakarta.persistence.EntityNotFoundException;

public class UpdateRecipeUseCaseImpl implements UpdateRecipeUseCase{

    private final RecipeGateway recipeGateway;

    public UpdateRecipeUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(Recipe recipe, Long id) {

        try{

            if(id < 0 || !recipeGateway.existsRecipeId(id)){
                throw new ResourceNotFoundException("Recipe with id " + id + " not found, cannot be deleted");
            }

            return recipeGateway.updateRecipe(recipe, id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recipe with id " + id + " not found, cannot be deleted");
        }

    }
}
