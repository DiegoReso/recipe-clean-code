package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;

public interface FindRecipeByIdentification {

    Recipe execute(String identification);

}
