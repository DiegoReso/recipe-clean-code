package dev.reso.recipeit.core.gateway;

import dev.reso.recipeit.core.entities.Recipe;

import java.util.List;

public interface RecipeGateway {

    Recipe createsRecipe(Recipe recipe);

    List<Recipe> findRecipes();

    boolean existsRecipeIdentification(String identification);

    Recipe findRecipeByIdentification(String identification);

    Recipe findRecipeById(Long id);

    boolean existsRecipeId(Long id);
}
