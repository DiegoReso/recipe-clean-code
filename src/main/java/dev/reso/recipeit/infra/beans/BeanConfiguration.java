package dev.reso.recipeit.infra.beans;

import dev.reso.recipeit.core.gateway.RecipeGateway;
import dev.reso.recipeit.core.useCases.CreatesRecipeUseCase;
import dev.reso.recipeit.core.useCases.CreatesRecipeUseCaseImpl;
import dev.reso.recipeit.core.useCases.FindRecipesUseCase;
import dev.reso.recipeit.core.useCases.FindRecipesUseCaseImpl;
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
}
