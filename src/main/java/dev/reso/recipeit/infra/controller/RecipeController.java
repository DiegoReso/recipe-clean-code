package dev.reso.recipeit.infra.controller;

import dev.reso.recipeit.core.entities.Recipe;
import dev.reso.recipeit.core.useCases.*;
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
    private final FindRecipeByIdentification findRecipeByIdentification;
    private final FindRecipeByIdUseCase findRecipeById;
    private final DeleteRecipeUseCase deleteRecipeUseCase;
    private final UpdateRecipeUseCase updateRecipeUseCase;
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

    @GetMapping("/{identification}")
    public ResponseEntity<RecipeDto> findRecipeByIdentification(@PathVariable String identification) {
        Recipe recipe = findRecipeByIdentification.execute(identification);
        return ResponseEntity.ok(mapper.toRecipeDto(recipe));
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<RecipeDto> findRecipeById(@PathVariable Long id) {
        Recipe recipe = findRecipeById.execute(id);
        return ResponseEntity.ok(mapper.toRecipeDto(recipe));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        deleteRecipeUseCase.execute(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateRecipe(@RequestBody RecipeDto recipeDto, @PathVariable Long id){
        Recipe recipe = updateRecipeUseCase.execute(mapper.toRecipe(recipeDto),id);
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("message: ", "Recipe updated successfully");
        response.put("data: ", mapper.toRecipeDto(recipe));
        return ResponseEntity.ok(response);
    }

}
