/*package com.recipe.recipeAssignment.recipeDemo.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;

@WebMvcTest
public class RecipeControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService recipeService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void givenRecipeObject_whenCreateRecipe_thenReturnSavedRecipe() throws Exception{
        // given - precondition or setup
        RecipeDto recipeDtoTest = RecipeDto.valueOf(RecipeDemo recipeDemo).builder()
                .recipeName("Egg Sandwich")
                .category("non-veg")
                .serveCapacity(1)
                .ingredients("Egg 2 nos,mushroom 5 nos,Salt 1 pinch,capsicum 4 nos,Tomato chopped 2 tbsp,onion 2 tbsp,Green Chili 2 nos,Coriander leaves chopped 1 tbsp,Garlic chopped 1 tsp,Butter 1 tbsp,Bread Slices 3 nos")
                .instructions("Chop Onion, tomato, capsicum, mushroom (3-4), garlic and green chili. Keep the capsicum slices ready. In a bowl, add the Eggs, Add all the chopped vegetables and salt, i.e, the Egg capsicum and bread, one over the other. Enjoy!")
                .build();
        given(recipeService.addRecipe(any(RecipeDto.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/recipes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recipeDtoTest)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.recipeName",
                        is(recipeDtoTest.getRecipeName())))
                .andExpect(jsonPath("$.category",
                        is(recipeDtoTest.getCategory())))
                .andExpect(jsonPath("$.serveCapacity",
                        is(recipeDtoTest.getServeCapacity())))
                .andExpect(jsonPath("$.ingredients",
                        is(recipeDtoTest.getIngredients())))
                .andExpect(jsonPath("$.instructions",
                        is(recipeDtoTest.getInstructions())));


    }


    }

*/