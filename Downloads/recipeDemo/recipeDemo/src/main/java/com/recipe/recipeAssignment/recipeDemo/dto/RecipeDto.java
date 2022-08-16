package com.recipe.recipeAssignment.recipeDemo.dto;

import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import lombok.Builder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


public class RecipeDto {
    @NotNull(message = "recipe Id shouldn't be null")
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


    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getServeCapacity() {
        return serveCapacity;
    }

    public void setServeCapacity(Integer serveCapacity) {
        this.serveCapacity = serveCapacity;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

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

    @Override
    public String toString() {
        return "RecipeDto{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", category='" + category + '\'' +
                ", serveCapacity=" + serveCapacity +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
