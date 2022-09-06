package com.recipe.recipeAssignment.recipeDemo.repository;

import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

/**
 * repository layer
 */

@Repository
public interface RecipeRepository extends JpaRepository<RecipeDemo,Integer> {
    List<RecipeDemo> getByCategory(String category);
    List<RecipeDemo> getByServeCapacity(Integer serveCapacity);
    RecipeDemo findByRecipeId(Integer recipeId);



    /**
     * Query {@link  RecipeDemo} by serveCapacity and ingredients
     * @param serveCapacity {@link  Integer} serve capacity of recipe
     * @param ingredients {@link  String} ingredients
     * @return {@link  List} of {@link RecipeDemo}
     */
    @Query("SELECT t1 FROM RecipeDemo as t1 WHERE t1.serveCapacity = ?1 and t1.ingredients like %?2%")
    List<RecipeDemo> findAllRecipeByServingCapacityAndIngredient(Integer serveCapacity , String ingredients);


}



