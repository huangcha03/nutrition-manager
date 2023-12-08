package com.nutritiontracker.nutritiontrackerbackend.ingredient.exception;

/**
 * A custom exception class to explain when ingredients were not located in the database
 */
public class IngredientNotFoundException extends RuntimeException {

    public IngredientNotFoundException(Long id) {
        super("Could not find food with id " + id);
    }
}
