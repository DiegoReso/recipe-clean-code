package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.InvalidArgumentException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;

public class FindRecipeByIdentificationImpl implements FindRecipeByIdentification {

    private final RecipeGateway recipeGateway;

    public FindRecipeByIdentificationImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(String identification) throws ResourceNotFoundException, InvalidArgumentException {

        if(identification == null || identification.isBlank()){
            throw new InvalidArgumentException("Identification cannot be null or empty.");
        }

        return recipeGateway.findRecipeByIdentification(identification);
    }
}
