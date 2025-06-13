package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;

public interface UpdateRecipeUseCase {

    Recipe execute(Recipe recipe, Long id);

}
