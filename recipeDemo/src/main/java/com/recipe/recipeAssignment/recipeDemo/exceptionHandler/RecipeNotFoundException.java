package com.recipe.recipeAssignment.recipeDemo.exceptionHandler;

import com.recipe.recipeAssignment.recipeDemo.service.RecipeService;

public class RecipeNotFoundException extends Exception {
    public RecipeNotFoundException(String message) {
        super(message);
    }
    public RecipeNotFoundException(Class<RecipeService> recipeServiceClass, String numberServing, String toString, String ingredient, String ingredients) {
    }
    public RecipeNotFoundException(Class<RecipeService> recipeServiceClass, String includeIngredient, String valueOf, String ingredient, String valueOf1, String instructions, String instructions1, String ingredient1, String ingredients) {
    }
}
