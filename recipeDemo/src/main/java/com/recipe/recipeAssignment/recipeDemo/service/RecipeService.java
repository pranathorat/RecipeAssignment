package com.recipe.recipeAssignment.recipeDemo.service;

import com.recipe.recipeAssignment.recipeDemo.dto.RecipeSearchDto;
import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeException;
import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import java.util.List;
/**
 * Service Interface
 */
public interface RecipeService {

    public List<RecipeDto> getAllRecipes() throws RecipeException;
    public RecipeDemo getRecipe(Integer recipeId) throws RecipeNotFoundException;
    public RecipeDemo addRecipe( RecipeDemo recipe);
    public List<RecipeDemo> deleteRecipe(Integer recipeId) throws RecipeIdNotExistException;
    public RecipeDemo updateRecipe(RecipeDto recipeDTO) throws RecipeException;
    List<RecipeDemo> getByCategory(String category);
    List<RecipeDemo>getByServeCapacity(Integer serveCapacity);
   // List<RecipeDemo> findRecipe(Integer serveCapacity,String ingredients) throws RecipeNotFoundException;
   List<RecipeDemo> findRecipe(RecipeSearchDto recipeSearchDto) throws RecipeNotFoundException;
 //   List<RecipeDemo> findRecipeByInstructionIngredient(String ingredients,String instructions) throws RecipeNotFoundException, RecipeException;
}
