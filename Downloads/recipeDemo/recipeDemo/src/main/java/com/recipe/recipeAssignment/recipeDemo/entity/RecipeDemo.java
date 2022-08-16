package com.recipe.recipeAssignment.recipeDemo.entity;

import javax.persistence.*;
@Entity
@Table(name="recipedemo")
public class RecipeDemo {
    @Id
    @Column(name="recipeid")
    Integer recipeId;
    @Column(name="recipename")
    String recipeName;
    @Column(name="category")
    String category;
    @Column(name="servecapacity")
    Integer serveCapacity;
    @Column(name="ingredients")
    String ingredients;
    @Column(name="instructions")
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

    @Override
    public String toString() {
        return "RecipeDemo{" +
                "recipeId=" + recipeId +
                ", recipeName='" + recipeName + '\'' +
                ", category='" + category + '\'' +
                ", serveCapacity=" + serveCapacity +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}