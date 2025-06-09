package dev.reso.recipeit.core.gateway;

import dev.reso.recipeit.core.entities.Recipe;

import java.util.List;

public interface RecipeGateway {

    Recipe createsRecipe(Recipe recipe);

    List<Recipe> findRecipes();

}
