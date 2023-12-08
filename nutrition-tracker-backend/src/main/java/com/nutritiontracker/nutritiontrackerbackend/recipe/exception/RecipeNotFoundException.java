package com.nutritiontracker.nutritiontrackerbackend.recipe.exception;

/**
 * A custome exception class to treat cases where recipes w/ a supplied id are not found
 */
public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(Long id) {
        super("Could not find recipe with id" + id);
    }
}
