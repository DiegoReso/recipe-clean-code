package dev.reso.recipeit.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
}
