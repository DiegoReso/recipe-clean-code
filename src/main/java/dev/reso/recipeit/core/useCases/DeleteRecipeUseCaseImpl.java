package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.exceptions.RecipeHasRelatedDataException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;

public class DeleteRecipeUseCaseImpl implements DeleteRecipeUseCase{

    private final RecipeGateway recipeGateway;

    public DeleteRecipeUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }


    @Override
    public void execute(Long id) throws ResourceNotFoundException, RecipeHasRelatedDataException {
        recipeGateway.deleteRecipe(id);

    }
}