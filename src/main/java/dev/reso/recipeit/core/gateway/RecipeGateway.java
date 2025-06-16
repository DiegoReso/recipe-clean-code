package dev.reso.recipeit.core.gateway;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.DuplicateRecipeKeyException;
import dev.reso.recipeit.core.exceptions.RecipeHasRelatedDataException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;

import java.util.List;

public interface RecipeGateway {

    Recipe createsRecipe(Recipe recipe) throws DuplicateRecipeKeyException;

    List<Recipe> findRecipes();

    boolean existsRecipeIdentification(String identification);

    Recipe findRecipeByIdentification(String identification)throws ResourceNotFoundException;

    Recipe findRecipeById(Long id) throws ResourceNotFoundException;

    boolean existsRecipeId(Long id);

    void deleteRecipe(Long id) throws ResourceNotFoundException, RecipeHasRelatedDataException;

    Recipe updateRecipe(Recipe recipe, Long id) throws ResourceNotFoundException, DuplicateRecipeKeyException;
}
