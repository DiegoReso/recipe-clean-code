package dev.reso.recipeit.infra.controller;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.useCases.CreatesRecipeUseCase;
import dev.reso.recipeit.core.useCases.FindRecipesUseCase;
import dev.reso.recipeit.infra.dtos.RecipeDto;
import dev.reso.recipeit.infra.mapper.RecipeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final CreatesRecipeUseCase createsRecipeCase;
    private final FindRecipesUseCase findRecipesUseCase;
    private final RecipeDtoMapper mapper;


    @PostMapping
    public RecipeDto createRecipe(@RequestBody RecipeDto recipeDto){
        Recipe recipe = createsRecipeCase.execute(mapper.toRecipe(recipeDto));
        return mapper.toRecipeDto(recipe);
    }

    @GetMapping
    public List<RecipeDto> findAllRecipes(){
        return findRecipesUseCase.excute().stream().map(mapper::toRecipeDto).toList();
    }

}
