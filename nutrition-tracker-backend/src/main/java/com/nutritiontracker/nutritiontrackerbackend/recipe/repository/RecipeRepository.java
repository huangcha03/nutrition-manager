package com.nutritiontracker.nutritiontrackerbackend.recipe.repository;

import com.nutritiontracker.nutritiontrackerbackend.recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A JPA repository for recipe objects
 */
public interface RecipeRepository extends JpaRepository<Recipe, Long> {


}
