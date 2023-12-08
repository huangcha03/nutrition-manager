package com.nutritiontracker.nutritiontrackerbackend.recipe.service;

import com.nutritiontracker.nutritiontrackerbackend.ingredient.model.Ingredient;
import com.nutritiontracker.nutritiontrackerbackend.ingredient.repository.IngredientRepository;
import com.nutritiontracker.nutritiontrackerbackend.recipe.exception.RecipeNotFoundException;
import com.nutritiontracker.nutritiontrackerbackend.recipe.model.Recipe;
import com.nutritiontracker.nutritiontrackerbackend.recipe.model.RecipeHandler;

import com.nutritiontracker.nutritiontrackerbackend.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A service for all methods related to the controller for Recipe objects
 */
@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    /**
     * Updates ingredient information with the updated info input by the user
     * @param newRecipe - the recipe object that contains the new recipe information
     * @param id - the id associated with the recipe we want to update
     * @exception RecipeNotFoundException -  if recipe with given id was not located
     * @return the updated recipe info being saved to the recipe repository
     */
    public Recipe updateIngredients(Recipe newRecipe, Long id) {
        return recipeRepository.findById(id)
                .map(recipe -> {
                    recipe.setRecipeName(newRecipe.getRecipeName());
                    recipe.setIngredients(newRecipe.getIngredients());

                    recipe.updateMacros();
                    return recipeRepository.save(recipe);
                }).orElseThrow(()->new RecipeNotFoundException(id));
    }


    /**
     * Retrieves the recipe with the given id from the database
     * @param id - the id of the recipe to retrieve
     * @exception RecipeNotFoundException - thrown if the id does not exist in the db
     * @return the recipe object of the given id
     */
    public Recipe getRecipe(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(()->new RecipeNotFoundException(id));
    }

    /**
     * Takes a recipe object and adds it to the database
     * @param recipeHandler - the object of the recipeHandler we want to add
     * @return Recipe object - the return of saving the recipe to the database
     */
    public Recipe addRecipe(RecipeHandler recipeHandler) {

        Recipe recipe = new Recipe();

        recipe.setRecipeName(recipeHandler.getRecipeName());

        Set<Long> ingredientIDs = recipeHandler.getIngredients();

        List<Ingredient> ingredients = ingredientRepository.findAllById(ingredientIDs);

        Set<Ingredient> ingredientSet = new HashSet<>(ingredients);

        recipe.setIngredients(ingredientSet);

        recipe.updateMacros();

        return recipeRepository.save(recipe);
    }

    /**
     * retrieves all recipes from the database
     * @return List - list of all recipes from the database
     */
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    /**
     * Deletes a recipe from the database
     * @param id - the id of the recipe we want to delete
     * @exception RecipeNotFoundException - thrown if the recipe with the ID does not exist
     * @return String - a success message
     */
    public String deleteRecipe(Long id) {
        Recipe food = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException(id));
        String foodName = food.getRecipeName();

        // to also remove the many-to-many relationship between
        // the food and its ingredients
        for (Ingredient ingredient : food.getIngredients()) {
            ingredient.getRecipes().remove(food);
        }

        recipeRepository.deleteById(id);
        return "Recipe " + foodName + " has been removed";
    }
}
