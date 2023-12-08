package com.nutritiontracker.nutritiontrackerbackend.ingredient.model;

import com.nutritiontracker.nutritiontrackerbackend.recipe.model.Recipe;
import jakarta.persistence.*;


import java.util.HashSet;

import java.util.Set;

/**
 * A class to represent an ingredient object
 */
@Entity
public class Ingredient {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double calories = 0;
    private double serving_size_g = 0;
    private double fat_total_g = 0;
    private double fat_saturated_g = 0;
    private double protein_g = 0;
    private int sodium_mg = 0;
    private int potassium_mg = 0;
    private int cholesterol_mg = 0;
    private double carbohydrates_total_g = 0;
    private double fiber_g = 0;
    private double sugar_g = 0;

    @ManyToMany
    @JoinTable(
            name = "ingredient_recipe",
            joinColumns = @JoinColumn(name="ingredient_id"),
            inverseJoinColumns = @JoinColumn(name="recipe_id")
    )
    private Set<Recipe> recipes = new HashSet<>();

    public Ingredient() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getServing_size_g() {
        return serving_size_g;
    }

    public void setServing_size_g(double serving_size_g) {
        this.serving_size_g = serving_size_g;
    }

    public double getFat_total_g() {
        return fat_total_g;
    }

    public void setFat_total_g(double fat_total_g) {
        this.fat_total_g = fat_total_g;
    }

    public double getFat_saturated_g() {
        return fat_saturated_g;
    }

    public void setFat_saturated_g(double fat_saturated_g) {
        this.fat_saturated_g = fat_saturated_g;
    }

    public double getProtein_g() {
        return protein_g;
    }

    public void setProtein_g(double protein_g) {
        this.protein_g = protein_g;
    }

    public int getSodium_mg() {
        return sodium_mg;
    }

    public void setSodium_mg(int sodium_mg) {
        this.sodium_mg = sodium_mg;
    }

    public int getPotassium_mg() {
        return potassium_mg;
    }

    public void setPotassium_mg(int potassium_mg) {
        this.potassium_mg = potassium_mg;
    }

    public int getCholesterol_mg() {
        return cholesterol_mg;
    }

    public void setCholesterol_mg(int cholesterol_mg) {
        this.cholesterol_mg = cholesterol_mg;
    }

    public double getCarbohydrates_total_g() {
        return carbohydrates_total_g;
    }

    public void setCarbohydrates_total_g(double carbohydrates_total_g) {
        this.carbohydrates_total_g = carbohydrates_total_g;
    }

    public double getFiber_g() {
        return fiber_g;
    }

    public void setFiber_g(double fiber_g) {
        this.fiber_g = fiber_g;
    }

    public double getSugar_g() {
        return sugar_g;
    }

    public void setSugar_g(double sugar_g) {
        this.sugar_g = sugar_g;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", serving_size_g=" + serving_size_g +
                ", fat_total_g=" + fat_total_g +
                ", fat_saturated_g=" + fat_saturated_g +
                ", protein_g=" + protein_g +
                ", sodium_mg=" + sodium_mg +
                ", potassium_mg=" + potassium_mg +
                ", cholesterol_mg=" + cholesterol_mg +
                ", carbohydrates_total_g=" + carbohydrates_total_g +
                ", fiber_g=" + fiber_g +
                ", sugar_g=" + sugar_g +
                '}';
    }
}
