package com.recipe.recipeAssignment.recipeDemo.exceptionHandler;

public class RecipeIdNotExistException extends Exception{
    public RecipeIdNotExistException(String message) {
        super(message);
    }
}
