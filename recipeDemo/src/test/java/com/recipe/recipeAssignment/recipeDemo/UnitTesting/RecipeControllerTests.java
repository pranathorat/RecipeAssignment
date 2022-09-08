package com.recipe.recipeAssignment.recipeDemo.UnitTesting;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.recipe.recipeAssignment.recipeDemo.controller.RecipeController;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import com.recipe.recipeAssignment.recipeDemo.service.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(controllers = RecipeController.class)
public class RecipeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private List<RecipeDto> recipeList;

    @BeforeEach
    void setUp() {
        this.recipeList = new ArrayList<>();
        this.recipeList.add(new RecipeDto(2, "Pulav", "veg", 1, "carrot", "cook"));
        this.recipeList.add(new RecipeDto(3, "biryani", "veg", 2, "vegetables", "make biryani"));

    }

    @Test
    void shouldFetchAllRecipes() throws Exception {

        given(recipeService.getAllRecipes()).willReturn(recipeList);

        this.mockMvc.perform(get("/recipes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(recipeList.size())));
    }

    @Test
    void shouldFetchOneRecipeById() throws Exception {
        final Integer recipeId = 2;
        final RecipeDemo recipe = new RecipeDemo(2, "Pulav", "veg", 1, "carrot", "cook");

        given(recipeService.getRecipe(recipeId)).willReturn((recipe));

        this.mockMvc.perform(get("/recipe/{recipeId}", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipeName", is(recipe.getRecipeName())));

    }

    @Test
    void shouldCreateNewRecipe() throws Exception {
        given(recipeService.addRecipe(new RecipeDemo())).willAnswer((invocation) -> invocation.getArgument(0));

        RecipeDemo recipedemo = new RecipeDemo(null, "Cake", "veg", 2, "Chocolate", "Oven");

        this.mockMvc.perform(post("/recipes")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(recipedemo)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldUpdateRecipe() throws Exception {
        Integer recipeId = 2;
        final RecipeDto recipe = new RecipeDto(2, "Pulav", "veg", 1, "carrot", "make");
        given(recipeService.getRecipe(recipeId)).willReturn((recipe.createEntity()));
        given(recipeService.updateRecipe(recipe)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/recipes/{recipeId}", recipe.getRecipeId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(recipe)))
                .andExpect(status().isOk());


    }

    @Test
    void shouldDeleteRecipe() throws Exception {
        Integer recipeId = 2;
        RecipeDemo recipe = recipeRepository.findByRecipeId(2);
        recipeRepository.deleteById(recipeId);
        RecipeDemo deletedRecipe = recipeRepository.findByRecipeId(2);
        assertThat(deletedRecipe).isNull();


    }

}


