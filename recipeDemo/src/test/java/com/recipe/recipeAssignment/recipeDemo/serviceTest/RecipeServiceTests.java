package com.recipe.recipeAssignment.recipeDemo.serviceTest;

import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import com.recipe.recipeAssignment.recipeDemo.service.RecipeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTests {
    @Mock
    RecipeRepository recipeRepository;

    RecipeServiceImpl recipeService;

    @BeforeEach
    void initUseCase() {
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void addRecipeTest() throws RecipeException {
        RecipeDemo recipeDemo = new RecipeDemo();
        recipeDemo.setRecipeId(3);
        recipeDemo.setRecipeName("Cake");
        recipeDemo.setCategory("Veg");
        recipeDemo.setServeCapacity(2);
        recipeDemo.setIngredients("Chocolate");
        recipeDemo.setInstructions("Oven");

        // providing knowledge
        when(recipeRepository.save(any(RecipeDemo.class))).thenReturn(recipeDemo);
        RecipeDemo savedRecipe = recipeRepository.save(recipeDemo);
        assertThat(savedRecipe.getRecipeId()).isNotNull();
    }
    @Test
    public void getAllRecipeTest() throws RecipeException{
        RecipeDemo recipeDemo = new RecipeDemo();
        recipeDemo.setRecipeId(3);
        recipeDemo.setRecipeName("Cake");
        recipeDemo.setCategory("Veg");
        recipeDemo.setServeCapacity(2);
        recipeDemo.setIngredients("Chocolate");
        recipeDemo.setInstructions("Oven");

        List<RecipeDemo> recipeDemoList = new ArrayList<>();
        recipeDemoList.add(recipeDemo);

        when(recipeRepository.findAll()).thenReturn(recipeDemoList);

        List<RecipeDto> fetchedRecipe = recipeService.getAllRecipes();
        assertThat(fetchedRecipe.size()).isGreaterThan(0);
    }

    @Test
    public void updateRecipeTest() throws RecipeException {
        RecipeDemo recipeDemo1 = new RecipeDemo();
        recipeDemo1.setRecipeId(3);
        recipeDemo1.setRecipeName("Cake");
        recipeDemo1.setCategory("Veg");
        recipeDemo1.setServeCapacity(2);
        recipeDemo1.setIngredients("Chocolate");
        recipeDemo1.setInstructions("Oven");

        when(recipeRepository.save(any(RecipeDemo.class))).thenReturn(recipeDemo1);
        RecipeDemo updateRecipe = recipeRepository.save(recipeDemo1);
        assertThat(updateRecipe.getIngredients()).isNotNull();

    }

    @Test
    public void deleteRecipeTest() throws  RecipeIdNotExistException {
        Assertions.assertThrows(RecipeIdNotExistException.class, () -> {
            List<RecipeDemo> fetchedRecipes = recipeService.deleteRecipe(17);
            assertThat(fetchedRecipes.size()).isGreaterThan(0);
        });
    }

}















