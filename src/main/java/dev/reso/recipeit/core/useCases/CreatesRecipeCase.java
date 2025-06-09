package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;

public interface CreatesRecipeCase {

    public Recipe execute(Recipe recipe);

}
