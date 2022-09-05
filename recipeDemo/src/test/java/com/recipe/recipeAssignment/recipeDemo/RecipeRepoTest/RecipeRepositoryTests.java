package com.recipe.recipeAssignment.recipeDemo.RecipeRepoTest;

import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeRepositoryTests {
    @Autowired
    RecipeRepository repository;

    @Test
    @Order(1)
    public void testAddRecipe() {
    RecipeDemo savedRecipe = repository.save(new RecipeDemo(2,"Pulav","veg",1,"carrot","cook"));
    assertThat(savedRecipe.getRecipeId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void testGetAllRecipes() {
        List<RecipeDemo> recipeDemoList = (List<RecipeDemo>) repository.findAll();
        assertThat(recipeDemoList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testUpdateRecipe() {
        RecipeDemo recipeDemo = repository.findByRecipeId(2);
        recipeDemo.setIngredients("GreenPeas");

        repository.save(recipeDemo);

        RecipeDemo updatedRecipe = repository.findByRecipeId(2);

        assertThat(updatedRecipe.getIngredients()).isEqualTo("GreenPeas");
    }

    @Test
    @Order(4)
    public void testDeleteRecipe() {
        RecipeDemo recipeDemo = repository.findByRecipeId(2);

        repository.deleteById(recipeDemo.getRecipeId());

         RecipeDemo deleteRecipe = repository.findByRecipeId(2);

        assertThat(deleteRecipe).isNull();

    }
}
