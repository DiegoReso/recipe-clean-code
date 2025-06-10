package dev.reso.recipeit.infra.controller;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.useCases.CreatesRecipeUseCase;
import dev.reso.recipeit.core.useCases.FindRecipesUseCase;
import dev.reso.recipeit.infra.dtos.RecipeDto;
import dev.reso.recipeit.infra.mapper.RecipeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final CreatesRecipeUseCase createsRecipeCase;
    private final FindRecipesUseCase findRecipesUseCase;
    private final RecipeDtoMapper mapper;


    @PostMapping
    public ResponseEntity<Map<String,Object>> createRecipe(@RequestBody RecipeDto recipeDto){
        Recipe recipe = createsRecipeCase.execute(mapper.toRecipe(recipeDto));
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("message: ", "Recipe created successfully");
        response.put("data: ", mapper.toRecipeDto(recipe));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<RecipeDto> findAllRecipes(){
        return findRecipesUseCase.excute().stream().map(mapper::toRecipeDto).toList();
    }

}
