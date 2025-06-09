package dev.reso.recipeit.infra.gateway;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.gateway.RecipeGateway;
import dev.reso.recipeit.infra.mapper.RecipeEntityMapper;
import dev.reso.recipeit.infra.persistence.RecipeEntity;
import dev.reso.recipeit.infra.persistence.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


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
}
