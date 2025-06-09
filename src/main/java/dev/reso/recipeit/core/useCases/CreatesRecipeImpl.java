package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.gateway.RecipeGateway;


public class CreatesRecipeImpl implements CreatesRecipeCase {

    private final RecipeGateway recipeGateway;

    public CreatesRecipeImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(Recipe recipe) {
        return recipeGateway.createsRecipe(recipe);
    }
}
