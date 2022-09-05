package com.recipe.recipeAssignment.recipeDemo.ExceptionHandler;

public class RecipeIdNotExistException extends Exception{
    public RecipeIdNotExistException(String message) {
        super(message);
    }
}
