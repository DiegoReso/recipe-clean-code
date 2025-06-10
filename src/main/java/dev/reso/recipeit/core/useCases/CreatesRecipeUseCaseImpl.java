package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.DuplicateRecipeKeyException;
import dev.reso.recipeit.core.gateway.RecipeGateway;


public class CreatesRecipeUseCaseImpl implements CreatesRecipeUseCase {

    private final RecipeGateway recipeGateway;

    public CreatesRecipeUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(Recipe recipe) {
        if(recipeGateway.existsRecipeIdentification(recipe.getIdentification())){
            throw new  DuplicateRecipeKeyException("key exists already -> " + recipe.getIdentification());
        }
        return recipeGateway.createsRecipe(recipe);
    }
}
