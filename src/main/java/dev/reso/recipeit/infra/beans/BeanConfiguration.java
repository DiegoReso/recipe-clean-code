package dev.reso.recipeit.infra.beans;

import dev.reso.recipeit.core.gateway.RecipeGateway;
import dev.reso.recipeit.core.useCases.CreatesRecipeCase;
import dev.reso.recipeit.core.useCases.CreatesRecipeImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public CreatesRecipeCase createsRecipe(RecipeGateway recipeGateway){
        return new CreatesRecipeImpl(recipeGateway);
    }
}
