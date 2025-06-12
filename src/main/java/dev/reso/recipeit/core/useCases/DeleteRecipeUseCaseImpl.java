package dev.reso.recipeit.core.useCases;

import dev.reso.recipeit.infra.persistence.DataBaseException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

public class DeleteRecipeUseCaseImpl implements DeleteRecipeUseCase{

    private final RecipeGateway recipeGateway;

    public DeleteRecipeUseCaseImpl(RecipeGateway recipeGateway) {
        this.recipeGateway = recipeGateway;
    }


    @Override
    public void execute(Long id) {
        try{
            if (!recipeGateway.existsRecipeId(id)){
                throw new ResourceNotFoundException("Recipe with id '" + id + "' not found, cannot be deleted");
            }
            recipeGateway.deleteRecipe(id);
        }catch (EmptyResultDataAccessException e){
            throw  new ResourceNotFoundException("Recipe with id '" + id + "' not found, cannot be deleted");
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException("Recipe with id '" + id + "' cannot be deleted due to integrity constraints");
        }
    }
}
