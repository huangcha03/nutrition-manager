package com.nutritiontracker.nutritiontrackerbackend.recipe.model;

import java.util.Set;

/**
 * A class to handle incoming JSON payloads of recipe objects
 * where the list of ingredients is a set of ingredient IDs
 */

public class RecipeHandler {

    private String recipeName;
    private Set<Long> ingredients;


    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Set<Long> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Long> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Long ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                ", recipeName='" + recipeName + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
