package com.recipe.recipeAssignment.recipeDemo.service;

import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;

import java.util.List;

/**
 * Service Interface
 */

public interface RecipeService {

    public List<RecipeDto> getAllRecipes() throws RecipeException;
    public RecipeDemo getRecipe(Integer recipeId) throws RecipeNotFoundException;
    public RecipeDemo addRecipe(RecipeDto recipeDto);
    //public Integer addRecipe(RecipeDto recipe) ;
    public List<RecipeDemo> deleteRecipe(Integer recipeId) throws RecipeIdNotExistException;
    public RecipeDemo updateRecipe(RecipeDto recipeDTO) throws RecipeException;
    List<RecipeDemo> getByCategory(String category);
    List<RecipeDemo>getByServeCapacity(Integer serveCapacity);

    List<RecipeDemo> findAllRecipeByServingCapacityAndIngredient(Integer serveCapacity, String ingredients) throws RecipeNotFoundException;
   public List<RecipeDemo> findAllRecipeByIngredientAndInstruction(boolean includeIngredient, String ingredients, boolean includeInstructions, String instructions) throws RecipeNotFoundException;

}
