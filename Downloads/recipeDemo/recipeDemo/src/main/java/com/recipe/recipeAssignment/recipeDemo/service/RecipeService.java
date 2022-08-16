package com.recipe.recipeAssignment.recipeDemo.service;

import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;


    //Get all recipes
    public List<RecipeDto> getAllRecipes() throws RecipeException {

        List<RecipeDemo> recipesDemo = recipeRepository.findAll();
        List<RecipeDto> recipeDTOs = new ArrayList<RecipeDto>();
        for (RecipeDemo recipe : recipesDemo) {
            RecipeDto recipeDTO = RecipeDto.valueOf(recipe);
            recipeDTOs.add(recipeDTO);
        }
        if (recipeDTOs.isEmpty())throw new RecipeException("Recipe_NOT_FOUND");

        return recipeDTOs;
    }
    //will get Recipe by RecipeId

    public RecipeDemo getRecipe(Integer recipeId) throws RecipeNotFoundException {
        RecipeDemo recipeDemo = recipeRepository.findByRecipeId(recipeId);


        if(recipeDemo!=null){
            log.info("Recipe Found By given Id");
            return recipeDemo;
        }
        else {
            log.error("Recipe not found By Given ID");
            throw new RecipeNotFoundException("Recipe Not Found with Recipe Id:"+recipeId);
        }
    }
    //
    public Integer addRecipe(RecipeDto recipe) throws RecipeException {

        RecipeDemo recipeEntity = recipe.createEntity();
        RecipeDemo recipeEntity2 = recipeRepository.save(recipeEntity);
        return recipeEntity2.getRecipeId();
    }

    public void deleteRecipe(Integer recipeId) throws RecipeIdNotExistException {
        Optional<RecipeDemo> recipe = recipeRepository.findById(recipeId);
        if(recipe!=recipeRepository.findById(recipeId)){
            log.info("Recipe ID Deleted");

        }
        else {
        log.error("Recipe ID Doesn't Exist");}
        recipe.orElseThrow(()->new RecipeIdNotExistException("Recipe Id "+recipeId+(" doesn't exist")));
        recipeRepository.deleteById(recipeId);

    }
       public RecipeDemo updateRecipe(RecipeDto recipeDTO) throws RecipeException {
        RecipeDemo recipe = recipeDTO.createEntity();
        recipe = recipeRepository.save(recipe);
        return recipe;
    }

}

