package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;

public interface FindRecipeByIdUseCase {

    Recipe execute(Long id);

}
