package dev.reso.recipeit.infra.mapper;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.infra.persistence.RecipeEntity;
import org.springframework.stereotype.Component;

@Component
public class RecipeEntityMapper {

    public RecipeEntity toRecipeEntity(Recipe recipe){
        return new RecipeEntity(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getInstruction(),
                recipe.getPreparationTimeMinutes(),
                recipe.getServings(),
                recipe.getCreatedAt(),
                recipe.getUpdatedAt(),
                recipe.getType()
        );
    }

    public Recipe recipeEntityToRecipe(RecipeEntity recipeEntity){
        return new Recipe(
                recipeEntity.getId(),
                recipeEntity.getName(),
                recipeEntity.getDescription(),
                recipeEntity.getInstruction(),
                recipeEntity.getPreparationTimeMinutes(),
                recipeEntity.getServings(),
                recipeEntity.getCreatedAt(),
                recipeEntity.getUpdatedAt(),
                recipeEntity.getType()
        );
    }
}
