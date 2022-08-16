package com.recipe.recipeAssignment.recipeDemo.repository;

import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<RecipeDemo,Integer> {
    List<RecipeDemo> getByCategory(String category);
    List<RecipeDemo> getByServeCapacity(Integer serveCapacity);
    RecipeDemo findByRecipeId(Integer recipeId);





}


