package com.recipe.recipeAssignment.recipeDemo.service;

import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    //Get all recipes
    public List<RecipeDto> getAllRecipes() throws RecipeException {

        List<RecipeDemo> recipesDemo = recipeRepository.findAll();
        List<RecipeDto> recipeDTOs = new ArrayList<RecipeDto>();
        for (RecipeDemo recipe : recipesDemo) {
            RecipeDto recipeDTO = RecipeDto.valueOf(recipe);
            recipeDTOs.add(recipeDTO);
        }
        if (recipeDTOs.isEmpty()) throw new RecipeException("Recipe_NOT_FOUND");

        return recipeDTOs;
    }
    //will get Recipe by RecipeId

    public RecipeDemo getRecipe(Integer recipeId) throws RecipeNotFoundException {
        RecipeDemo recipeDemo = recipeRepository.findByRecipeId(recipeId);

        if (recipeDemo != null) {
            log.info("Recipe Found By given Id");
            return recipeDemo;
        } else {
            log.error("Recipe not found By Given ID");
            throw new RecipeNotFoundException("Recipe Not Found with Recipe Id:" + recipeId);
        }
    }
    public RecipeDemo addRecipe(RecipeDto recipeDTO) {
        RecipeDemo recipeDemo = recipeDTO.createEntity();
        return recipeRepository.save(recipeDemo);
    }
    public List<RecipeDemo> deleteRecipe(Integer recipeId) throws RecipeIdNotExistException {
        Optional<RecipeDemo> recipe = recipeRepository.findById(recipeId);
        if (recipe != recipeRepository.findById(recipeId)) {
            log.info("Recipe ID Deleted");

        } else {
            log.error("Recipe ID Doesn't Exist");
        }
        recipe.orElseThrow(() -> new RecipeIdNotExistException("Recipe Id " + recipeId + (" doesn't exist")));
        recipeRepository.deleteById(recipeId);

        return null;
    }

    public RecipeDemo updateRecipe(RecipeDto recipeDTO) throws RecipeException {
        RecipeDemo recipe = recipeDTO.createEntity();
        recipe = recipeRepository.save(recipe);
        return recipe;
    }
    public List<RecipeDemo> getByCategory(String category) {
        List<RecipeDemo> recipesList = recipeRepository.getByCategory(category);

        return recipesList;
    }
    public List<RecipeDemo> getByServeCapacity(Integer serveCapacity){
        List<RecipeDemo> recipeDemoList = recipeRepository.getByServeCapacity(serveCapacity);

        return recipeDemoList;
    }
        @Override
    public List<RecipeDemo> findAllRecipeByServingCapacityAndIngredient(Integer serveCapacity, String ingredients) throws RecipeNotFoundException {

        List<RecipeDemo> recipesList = recipeRepository.findAllRecipeByServingCapacityAndIngredient(serveCapacity, ingredients);

        if (recipesList.isEmpty()) {
            throw new RecipeNotFoundException(RecipeService.class, "serveCapacity", serveCapacity.toString(), "ingredient", ingredients);
        }
        return recipesList;
    }

    public List<RecipeDemo> findAllRecipeByIngredientAndInstruction(boolean includeIngredient, String ingredients, boolean includeInstructions, String instructions) throws RecipeNotFoundException {
        List<RecipeDemo> recipesList = recipeRepository.findAll().stream().filter(recipeDemo -> {
                    boolean finalIncludeIngredient;
                    boolean finalIncludeInstructions;
                    if (includeIngredient) {
                        finalIncludeIngredient = recipeDemo.getIngredients().contains(ingredients);
                    } else {
                        finalIncludeIngredient = !recipeDemo.getIngredients().contains(ingredients);
                    }
                    if (includeInstructions) {
                        finalIncludeInstructions = recipeDemo.getInstructions().contains(instructions);
                    } else {
                        finalIncludeInstructions = !recipeDemo.getInstructions().contains(instructions);
                    }
                    return finalIncludeIngredient && finalIncludeInstructions;
                }

        ).collect(Collectors.toList());

        if (recipesList.isEmpty()) {
            throw new RecipeNotFoundException(RecipeService.class, "includeIngredient", String.valueOf(includeIngredient), "ingredient", String.valueOf(includeInstructions), "instructions", instructions, "ingredient",ingredients);
        }
        return recipesList;

    }
}


