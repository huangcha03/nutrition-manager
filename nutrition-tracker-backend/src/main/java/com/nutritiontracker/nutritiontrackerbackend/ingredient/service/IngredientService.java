package com.nutritiontracker.nutritiontrackerbackend.ingredient.service;
import com.nutritiontracker.nutritiontrackerbackend.ingredient.exception.IngredientNotFoundException;
import com.nutritiontracker.nutritiontrackerbackend.ingredient.model.Ingredient;
import com.nutritiontracker.nutritiontrackerbackend.ingredient.repository.IngredientRepository;
import com.nutritiontracker.nutritiontrackerbackend.recipe.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


/**
 * A service class for ingredient methods
 */
@Service
public class IngredientService {
    private String apiKey = "qEmzI8Kc4qWszKpNHLI0WQ==wl1lPummmwoMhiV2";
    private String api_url = "https://api.api-ninjas.com/v1/nutrition?query=";
    private WebClient.Builder builder = WebClient.builder();

    @Autowired
    private IngredientRepository ingredientRepository;

    /**
     * Creates a new ingredient based off the query suuplied
     * @param query - the name of the food we want to add to the database
     * @return Ingredient - the ingredient object being saved to the database
     */
    public ResponseEntity<Long> createNewIngredient(String query) {
        String request = api_url + query;

        Ingredient[] ingredients = builder.build()
                .get()
                .uri(request)
                .header("X-Api-Key", apiKey)
                .retrieve()
                .bodyToMono(Ingredient[].class)
                .block();

        Ingredient ingredient = ingredients[0];

        // prevent duplicates from being saved again
        ingredientRepository.save(ingredient);

        Long addedId = ingredient.getId();

        return ResponseEntity.ok(addedId);
    }

    /**
     * Retrieves the ingredient with the supplied id
     * @param id - the id of the ingredient we want to retrieve
     * @exception IngredientNotFoundException - if the ingredient with the supplied id does not exist in the database
     * @return Ingredient - the ingredient object we want
     */
    public Ingredient getIngredient(Long id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
    }

    /**
     * A method to delete the ingredient with the supplied id
     * @param id - id of the ingredient we want to delete
     * @exception IngredientNotFoundException - thrown if the ingredient does not exist
     * @return String - a success message
     */
    public String deleteIngredient(Long id) {

        Ingredient food = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException(id));
        String foodName = food.getName();

        // to also remove the many-to-many relationship between
        // the food and its recipes
        for (Recipe recipe : food.getRecipes()) {
            recipe.getIngredients().remove(food);
        }
        ingredientRepository.deleteById(id);
        return "Ingredient " + foodName + " has been removed";
    }

    /**
     * A method to edit the ingredient of the supplied id with the information of a new ingredient
     * @param newIngredient - the ingredient object that houses all the updated information
     * @param id - id of the ingredient we want to modify
     * @exception IngredientNotFoundException - thrown if the ingredient with the given id does not exist
     * @return Ingredient - the updated ingredient
     */
    public Ingredient editIngredient(Ingredient newIngredient, Long id) {
        return ingredientRepository.findById(id)
                .map(ingredient -> {
                    ingredient.setName(newIngredient.getName());
                    ingredient.setCalories(newIngredient.getCalories());
                    ingredient.setServing_size_g(newIngredient.getServing_size_g());
                    ingredient.setFat_total_g(newIngredient.getFat_total_g());
                    ingredient.setFat_saturated_g(newIngredient.getFat_saturated_g());
                    ingredient.setProtein_g(newIngredient.getProtein_g());
                    ingredient.setSodium_mg(newIngredient.getSodium_mg());
                    ingredient.setPotassium_mg(newIngredient.getPotassium_mg());
                    ingredient.setCholesterol_mg(newIngredient.getCholesterol_mg());
                    ingredient.setCarbohydrates_total_g(newIngredient.getCarbohydrates_total_g());
                    ingredient.setFiber_g(newIngredient.getFiber_g());
                    ingredient.setSugar_g(newIngredient.getSugar_g());
                    ingredient.setRecipes(newIngredient.getRecipes());

                    return ingredientRepository.save(ingredient);
                }).orElseThrow(() -> new IngredientNotFoundException(id));
    }

    /**
     * Retrieves all ingredients from the database
     * @return List - of all ingredient objects
     */
    public List<Ingredient> getAll() {
        return ingredientRepository.findAll();
    }
}
