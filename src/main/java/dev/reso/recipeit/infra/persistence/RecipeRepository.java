package dev.reso.recipeit.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {

    Optional<RecipeEntity> findByIdentification(String identification);

}
