package dev.reso.recipeit.infra.mapper;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.infra.dtos.RecipeDto;
import dev.reso.recipeit.infra.persistence.RecipeEntity;
import org.springframework.stereotype.Component;

@Component
public class RecipeDtoMapper {

    public RecipeDto toRecipeDto(Recipe recipe){
        return new RecipeDto(
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

    public Recipe toRecipe (RecipeDto recipeDto){
        return new Recipe(
                recipeDto.id(),
                recipeDto.name(),
                recipeDto.description(),
                recipeDto.instruction(),
                recipeDto.preparationTimeMinutes(),
                recipeDto.servings(),
                recipeDto.createdAt(),
                recipeDto.updatedAt(),
                recipeDto.type()
        );
    }
}
