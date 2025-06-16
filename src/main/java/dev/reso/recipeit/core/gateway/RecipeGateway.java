package dev.reso.recipeit.core.gateway;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.DuplicateRecipeKeyException;

import java.util.List;

public interface RecipeGateway {

    Recipe createsRecipe(Recipe recipe) throws DuplicateRecipeKeyException;

    List<Recipe> findRecipes();

    boolean existsRecipeIdentification(String identification);

    Recipe findRecipeByIdentification(String identification);

    Recipe findRecipeById(Long id);

    boolean existsRecipeId(Long id);

    void deleteRecipe(Long id);

    Recipe updateRecipe(Recipe recipe, Long id);
}
