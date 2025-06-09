package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.gateway.RecipeGateway;

import java.util.List;

public class FindRecipesUseCaseImpl implements FindRecipesUseCase{

    private RecipeGateway recipeGateway;

    public FindRecipesUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public List<Recipe> excute() {
        return recipeGateway.findRecipes();
    }
}
