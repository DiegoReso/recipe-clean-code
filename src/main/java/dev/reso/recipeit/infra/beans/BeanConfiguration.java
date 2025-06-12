package dev.reso.recipeit.infra.beans;

import dev.reso.recipeit.core.gateway.RecipeGateway;
import dev.reso.recipeit.core.useCases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreatesRecipeUseCase createsRecipe(RecipeGateway recipeGateway){
        return new CreatesRecipeUseCaseImpl(recipeGateway);
    }

    @Bean
    public FindRecipesUseCase findRecipesUseCase(RecipeGateway recipeGateway){
        return new FindRecipesUseCaseImpl(recipeGateway);
    }

    @Bean
    public FindRecipeByIdentification findRecipeByIdentification(RecipeGateway recipeGateway) {
        return new FindRecipeByIdentificationImpl(recipeGateway);
    }

    @Bean
    public FindRecipeByIdUseCase findRecipeByIdUseCase(RecipeGateway recipeGateway) {
        return new FindRecipeByIdUseCaseImpl(recipeGateway);
    }
}
