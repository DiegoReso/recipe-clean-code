package dev.reso.recipeit.infra.controller;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.useCases.CreatesRecipeCase;
import dev.reso.recipeit.infra.dtos.RecipeDto;
import dev.reso.recipeit.infra.mapper.RecipeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final CreatesRecipeCase createsRecipeCase;
    private final RecipeDtoMapper mapper;


    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto){
        Recipe recipe = createsRecipeCase.execute(mapper.toRecipe(recipeDto));
        return mapper.toRecipeDto(recipe);
    }

}
