package dev.reso.recipeit.infra.gateway;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import dev.reso.recipeit.infra.mapper.RecipeEntityMapper;
import dev.reso.recipeit.infra.persistence.RecipeEntity;
import dev.reso.recipeit.infra.persistence.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class RecipeRepositoryGateway implements RecipeGateway {

    private final RecipeRepository repository;
    private final RecipeEntityMapper mapper;

    @Override
    public Recipe createsRecipe(Recipe recipe) {
        RecipeEntity entity = mapper.toRecipeEntity(recipe);
        RecipeEntity newEntity = repository.save(entity);
        return mapper.recipeEntityToRecipe(newEntity);
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
}
