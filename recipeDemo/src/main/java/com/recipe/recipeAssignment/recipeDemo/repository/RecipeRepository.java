package com.recipe.recipeAssignment.recipeDemo.repository;

import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * repository layer
 */

@Repository
public interface RecipeRepository extends JpaRepository <RecipeDemo,Integer> {



    List<RecipeDemo> getByCategory(String category);
    List<RecipeDemo> getByServeCapacity(Integer serveCapacity);
    RecipeDemo findByRecipeId(Integer recipeId);






}



