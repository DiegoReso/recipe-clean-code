package dev.reso.recipeit.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

}
