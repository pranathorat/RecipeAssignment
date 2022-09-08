package com.recipe.recipeAssignment.recipeDemo.integrationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class IntegrationTests {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;


    private String getRootUrl() {
        return "http://localhost:" + port;
    }


    @Test
    public void testGetAllRecipes() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/recipes",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetRecipeById() {
        RecipeDemo recipeDemo = restTemplate.getForObject(getRootUrl() + "/recipe/3", RecipeDemo.class);
        System.out.println(recipeDemo.getRecipeName());
        assertNotNull(recipeDemo);
    }

    @Test
    public void testCreateRecipe() {
        RecipeDemo recipeDemo = new RecipeDemo();
        recipeDemo.setRecipeName("pastaaa");
        recipeDemo.setServeCapacity(1);
        recipeDemo.setCategory("veg");
        recipeDemo.setIngredients("cheese");
        recipeDemo.setInstructions("make it spicy");
        ResponseEntity<RecipeDemo> postResponse = restTemplate.postForEntity(getRootUrl() + "/recipes", recipeDemo, RecipeDemo.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateRecipe() {
        int recipeId = 3;
        RecipeDto recipeDTO = restTemplate.getForObject(getRootUrl() + "/recipes/" + recipeId, RecipeDto.class);
        recipeDTO.setRecipeName("Chicken Momo");
        recipeDTO.setServeCapacity(6);
        restTemplate.put(getRootUrl() + "/recipes/" + recipeId, recipeDTO);
        RecipeDto updatedRecipe = restTemplate.getForObject(getRootUrl() + "/recipes/" + recipeId, RecipeDto.class);
        assertNotNull(updatedRecipe);
    }

    @Test
    public void testDeleteRecipe() {
        int recipeId = 6;
        RecipeDto recipeDTO = restTemplate.getForObject(getRootUrl() + "/recipes/" + recipeId, RecipeDto.class);
        assertNotNull(recipeDTO);
        restTemplate.delete(getRootUrl() + "/recipes/" + recipeId);
        try {
            recipeDTO = restTemplate.getForObject(getRootUrl() + "/recipes/" + recipeId, RecipeDto.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}