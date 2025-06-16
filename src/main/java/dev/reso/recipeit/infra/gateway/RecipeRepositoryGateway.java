package dev.reso.recipeit.infra.gateway;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.exceptions.DuplicateRecipeKeyException;
import dev.reso.recipeit.core.exceptions.InvalidArgumentException;
import dev.reso.recipeit.core.exceptions.RecipeHasRelatedDataException;
import dev.reso.recipeit.core.exceptions.ResourceNotFoundException;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import dev.reso.recipeit.infra.mapper.RecipeEntityMapper;
import dev.reso.recipeit.infra.persistence.RecipeEntity;
import dev.reso.recipeit.infra.persistence.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class RecipeRepositoryGateway implements RecipeGateway {

    private final RecipeRepository repository;
    private final RecipeEntityMapper mapper;

    @Override
    public Recipe createsRecipe(Recipe recipe) throws DuplicateRecipeKeyException {

        try{
            RecipeEntity entity = mapper.toRecipeEntity(recipe);
            RecipeEntity newEntity = repository.save(entity);
            return mapper.recipeEntityToRecipe(newEntity);
        }catch (DataIntegrityViolationException e){
            throw  new DuplicateRecipeKeyException("The identificacion " + recipe.getIdentification() + " already " +
                    "exists");
        }
    }

    @Override
    public List<Recipe> findRecipes() {
        List<RecipeEntity> listRecipe = repository.findAll();
        return listRecipe.stream().map(mapper::recipeEntityToRecipe).toList();
    }

    @Override
    public boolean existsRecipeIdentification(String identification) {
        return repository.findAll()
                .stream()
                .anyMatch(recipe -> recipe.getIdentification().equalsIgnoreCase(identification));
    }

    @Override
    public Recipe findRecipeByIdentification(String identification) {
        RecipeEntity recipeEntity = repository.findByIdentification(identification)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe with identification '" + identification + "' not found."));
       return mapper.recipeEntityToRecipe(recipeEntity);
    }

    @Override
    public Recipe findRecipeById(Long id) throws ResourceNotFoundException {
        RecipeEntity recipeEntity = repository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Recipe with ID '" + id + "' not found."));

        return mapper.recipeEntityToRecipe(recipeEntity);
    }

    @Override
    public boolean existsRecipeId(Long id) {
        return repository.findAll()
                .stream()
                .anyMatch(recipe -> recipe.getId().equals(id));
    }

    @Override
    public void deleteRecipe(Long id) throws ResourceNotFoundException,RecipeHasRelatedDataException {

        if(!existsRecipeId(id)) {
            throw new ResourceNotFoundException("Recipe with id '" + id + "' not found");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RecipeHasRelatedDataException("Recipe with id '" + id + "' cannot be deleted as it has related data.");
        }
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws ResourceNotFoundException, DuplicateRecipeKeyException {
        try {
            RecipeEntity entity = mapper.toRecipeEntity(recipe);
            entity.setId(id);
            RecipeEntity updatedEntity = repository.save(entity);
            return mapper.recipeEntityToRecipe(updatedEntity);
        } catch (DataIntegrityViolationException e) {
            if(e.getMessage().contains("recipe_identification_key"))
            {
                throw new DuplicateRecipeKeyException("The identification " + recipe.getIdentification() + " already exists");
            } else {
                throw new InvalidArgumentException("Recipe with ID '" + recipe.getIdentification() + "' too long for" +
                        " indentification");
            }
        }
    }
}
