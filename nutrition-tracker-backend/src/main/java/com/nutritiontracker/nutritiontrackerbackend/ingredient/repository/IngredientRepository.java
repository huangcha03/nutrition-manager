package com.nutritiontracker.nutritiontrackerbackend.ingredient.repository;

import com.nutritiontracker.nutritiontrackerbackend.ingredient.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A JPA repository for ingredient objects
 */
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    // method here that checks if the name of the food is already in the repository

}
