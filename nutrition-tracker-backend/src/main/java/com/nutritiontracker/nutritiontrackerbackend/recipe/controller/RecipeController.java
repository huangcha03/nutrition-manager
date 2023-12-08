package com.nutritiontracker.nutritiontrackerbackend.recipe.controller;

import com.nutritiontracker.nutritiontrackerbackend.recipe.exception.RecipeNotFoundException;
import com.nutritiontracker.nutritiontrackerbackend.recipe.model.Recipe;
import com.nutritiontracker.nutritiontrackerbackend.recipe.model.RecipeHandler;
import com.nutritiontracker.nutritiontrackerbackend.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A controller for recipe objects
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class RecipeController {

    @Autowired
    RecipeService rs = new RecipeService();

    @PutMapping("/recipes/{id}")
    public Recipe updateIngredients(@RequestBody Recipe newRecipe, @PathVariable Long id) {
        return rs.updateIngredients(newRecipe, id);
    }

    /**
     * reads the supplied information and saves it to a recipe object in the sql database
     * @param recipeHandler - the recipe we want to save
     * @return Recipe - the recipe saved in the database
     */
    // FIGURE OUT WHAT ANNOTATION TO USE FOR INGREDIENT IDS
    @PostMapping("/addRecipe")
    public Recipe addRecipe(@RequestBody RecipeHandler recipeHandler) {

        return rs.addRecipe(recipeHandler);
    }

    /**
     * Retrieves all recipes from the SQL database
     * @return List - of all recipes
     */
    @GetMapping("/recipes")
    public List<Recipe> getAllRecipes() {
        return rs.findAll();
    }

    /**
     * Retrieves the recipe object with the supplied id
     * @param id - the id of the recipe we want to retrieve
     * @return Recipe - the recipe w/ the given id
     */
    @GetMapping("/recipes/{id}")
    public Recipe viewRecipe(@PathVariable Long id) {
        return rs.getRecipe(id);
    }

    /**
     * Deletes a recipe based off the id associated with it
     * @param id - the id mapped to the recipe
     * @exception RecipeNotFoundException - thrown if the ingredient with he specified id was unable to be located
     * @return a string signifying if the ingredient has been deleted
     */
    @DeleteMapping("/recipes/{id}")
    public String deleteRecipe(@PathVariable Long id) {
        return rs.deleteRecipe(id);
    }
}
