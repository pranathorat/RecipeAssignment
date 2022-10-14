package com.recipe.recipeAssignment.recipeDemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearchDto {


    String category;
    Integer serveCapacity;
    String ingredients;
    String instructions;
    boolean includeInstructions;
    boolean includeIngredients;

}
