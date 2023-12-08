package com.nutritiontracker.nutritiontrackerbackend.recipe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nutritiontracker.nutritiontrackerbackend.ingredient.model.Ingredient;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * A class to represent a recipe object
 */
@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private Long id;
    private String recipeName;

    @JsonIgnore
    @ManyToMany(mappedBy = "recipes")
    private Set<Ingredient> ingredients = new HashSet<>();
    private double total_calories = 0;
    private double total_carbohydrates = 0;
    private double total_sugar = 0;
    private double total_fat = 0;
    private double total_saturated_fat = 0;
    private double total_protein = 0;
    private double total_cholesterol = 0;

    /**
     * A method to update all the total macros of this recipe
     * using the list of ingredients used in the recipe
     */

    public void updateMacros() {

        for (Ingredient ingredient : this.ingredients) {
            this.total_calories += ingredient.getCalories();
            this.total_carbohydrates += ingredient.getCarbohydrates_total_g();
            this.total_cholesterol += ingredient.getCholesterol_mg();
            this.total_fat += ingredient.getFat_total_g();
            this.total_saturated_fat += ingredient.getFat_saturated_g();
            this.total_sugar += ingredient.getSugar_g();
            this.total_protein += ingredient.getProtein_g();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {

        this.ingredients = ingredients;

        // to update the ingredients in the many-to-many relationship
        for (Ingredient ingredient : ingredients) {

            ingredient.getRecipes().add(this);

        }


    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public double getTotal_calories() {
        return total_calories;
    }

    public void setTotal_calories(double total_calories) {
        this.total_calories = total_calories;
    }

    public double getTotal_carbohydrates() {
        return total_carbohydrates;
    }

    public void setTotal_carbohydrates(double total_carbohydrates) {
        this.total_carbohydrates = total_carbohydrates;
    }

    public double getTotal_sugar() {
        return total_sugar;
    }

    public void setTotal_sugar(double total_sugar) {
        this.total_sugar = total_sugar;
    }

    public double getTotal_fat() {
        return total_fat;
    }

    public void setTotal_fat(double total_fat) {
        this.total_fat = total_fat;
    }

    public double getTotal_saturated_fat() {
        return total_saturated_fat;
    }

    public void setTotal_saturated_fat(double total_saturated_fat) {
        this.total_saturated_fat = total_saturated_fat;
    }

    public double getTotal_protein() {
        return total_protein;
    }

    public void setTotal_protein(double total_protein) {
        this.total_protein = total_protein;
    }

    public double getTotal_cholesterol() {
        return total_cholesterol;
    }

    public void setTotal_cholesterol(double total_cholesterol) {
        this.total_cholesterol = total_cholesterol;
    }
}
