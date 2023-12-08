package com.nutritiontracker.nutritiontrackerbackend.ingredient.controller;

import com.nutritiontracker.nutritiontrackerbackend.ingredient.exception.IngredientNotFoundException;
import com.nutritiontracker.nutritiontrackerbackend.ingredient.model.Ingredient;

import com.nutritiontracker.nutritiontrackerbackend.ingredient.service.IngredientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A controller class to manage ingredient objects
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class IngredientController {

    @Autowired
    private IngredientService service = new IngredientService();

    /**
     * Creates a new ingredient using the input from the website specified by the user
     * @param food - the name of the ingredient the user would like to add to their ingredients list
     * @return Ingredient - saves that ingredient to the SQL database
     */
    @PostMapping("/addIngredient/{food}")
    public ResponseEntity<Long> newIngredient(@PathVariable String food) {
        // parse API call using the string name parameter here
        return service.createNewIngredient(food);
    }


    /**
     * displays the ingredient with the corresponding id's information
     * @param id - the id associated with the ingredient
     * @exception IngredientNotFoundException - thrown if the ingredient with he specified id was unable to be located
     * @return Ingredient - the ingredient object from the database
     */
    @GetMapping("/ingredients/{id}")
    public Ingredient viewIngredientParam(@PathVariable Long id) {
        return service.getIngredient(id);
    }


    /**
     * Deletes an ingredient based off the id associated with it
     * @param id - the id mapped to the ingredient
     * @exception IngredientNotFoundException - thrown if the ingredient with he specified id was unable to be located
     * @return a string signifying if the ingredient has been deleted
     */
    @DeleteMapping("/ingredients/{id}")
    public String deleteIngredient(@PathVariable Long id) {
        return service.deleteIngredient(id);
    }

    /**
     * Edits the ingredient with user input information
     * @param newIngredient - the new ingredient information input by the user
     * @param id - the id of the ingredient we want to edit
     * @exception IngredientNotFoundException - thrown if the ingredient with he specified id was unable to be located
     * @return Ingredient - the new ingredient being saved to the database
     */
    @PutMapping("/ingredients/{id}/edit")
    public Ingredient editIngredient(@RequestBody Ingredient newIngredient, @PathVariable Long id) {
        return service.editIngredient(newIngredient, id);
    }

    /**
     * retrieves all ingredients from the database
     * @return list - of all the ingredients
     */
    @GetMapping("/ingredients")
    public List<Ingredient> getAllIngredients() {
        return service.getAll();
    }
}
