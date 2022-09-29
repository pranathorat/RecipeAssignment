package com.recipe.recipeAssignment.recipeDemo.dto;

import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeDto {

    Integer recipeId;
    @NotNull(message = "recipe Name shouldn't be null")
    String recipeName;
    @NotNull(message = "recipe Category shouldn't be null")
    String category;
    @Min(1)
    @Max(10)
    Integer serveCapacity;
    String ingredients;
    String instructions;


    public RecipeDto(Integer recipeId, String recipeName, String category, Integer serveCapacity, String ingredients, String instructions) {
       super();
        this.recipeId = recipeId;
        this.recipeName = recipeName;
        this.category = category;
        this.serveCapacity = serveCapacity;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    /**
     *  Converts Entity into DTO
     * @param recipeDemo entity values
     * @return recipeDTO
     */
    public static RecipeDto valueOf(RecipeDemo recipeDemo) {
        RecipeDto recipeDTO= new RecipeDto();
        recipeDTO.setRecipeId(recipeDemo.getRecipeId());
        recipeDTO.setRecipeName(recipeDemo.getRecipeName());
        recipeDTO.setCategory(recipeDemo.getCategory());
        recipeDTO.setServeCapacity(recipeDemo.getServeCapacity());
        recipeDTO.setIngredients(recipeDemo.getIngredients());
        recipeDTO.setInstructions(recipeDemo.getInstructions());

        return recipeDTO;

    }

    public RecipeDemo createEntity() {
        RecipeDemo recipeDemo = new RecipeDemo();
        recipeDemo.setRecipeId(this.getRecipeId());
        recipeDemo.setRecipeName(this.getRecipeName());
        recipeDemo.setCategory(this.getCategory());
        recipeDemo.setServeCapacity(this.getServeCapacity());
        recipeDemo.setIngredients(this.getIngredients());
        recipeDemo.setInstructions(this.getInstructions());

        return recipeDemo;
    }

}
