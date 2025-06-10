package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import jakarta.persistence.EntityNotFoundException;

public class FindRecipeByIdentificationImpl implements FindRecipeByIdentification {

    private final RecipeGateway recipeGateway;

    public FindRecipeByIdentificationImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }

    @Override
    public Recipe execute(String identification) {
        try{
            if(identification == null || identification.isBlank()){
                throw new ResourceNotFoundException("Identification cannot be null or empty");
            }
            if(!recipeGateway.existsRecipeIdentification(identification)){
                throw new ResourceNotFoundException("Recipe with identification '" + identification + "' not found");
            }

            return recipeGateway.findRecipeById(identification);
        } catch (IllegalArgumentException e) {
            throw new ResourceNotFoundException("Invalid identification provided: " + identification);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recipe with identification '" + identification + "' not found");
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while finding the recipe", e);
        }

    }
}
