package com.recipe.recipeAssignment.recipeDemo.service;

import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeException;
import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service layer Implementation
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    /**
     * will Get all recipes using getAllRecipes method
     *
     * @return {@link List} of {@link RecipeDto}
     * @throws RecipeException
     */
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

    /**
     * will get Recipe by RecipeId
     *
     * @param recipeId {@link Integer} Recipe ID
     * @return {@link RecipeDemo} if recipe is present by Given Recipe ID
     * @throws RecipeNotFoundException
     */
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

    /**
     * It will create new Recipe
     *
     * @param
     * @return {@link RecipeDemo}values
     */

    public RecipeDemo addRecipe(RecipeDemo recipeDemo) {
        return recipeRepository.save(recipeDemo);
    }

    /**
     * It will delete recipe By recipe Id
     *
     * @param recipeId {@link Integer} recipeID
     * @throws RecipeIdNotExistException
     */
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

    /**
     * It will update the recipe
     *
     * @param recipeDTO
     * @return {@link RecipeDemo}
     * @throws RecipeException
     */

    public RecipeDemo updateRecipe(RecipeDto recipeDTO) throws RecipeException {
        RecipeDemo recipe = recipeDTO.createEntity();
        recipe = recipeRepository.save(recipe);
        return recipe;
    }

    /**
     * It will return recipes by category i.e. recipe is veg or non-veg
     *
     * @param category
     * @return {@link List} of {@link RecipeDemo}
     */
    public List<RecipeDemo> getByCategory(String category) {
        List<RecipeDemo> recipesList = recipeRepository.getByCategory(category);

        return recipesList;
    }

    /**
     * It will return recipes by serving count
     *
     * @param serveCapacity {@link Integer} serveCapacity
     * @return {@link List} of {@link RecipeDemo} by serve capacity
     */
    public List<RecipeDemo> getByServeCapacity(Integer serveCapacity) {
        List<RecipeDemo> recipeDemoList = recipeRepository.getByServeCapacity(serveCapacity);

        return recipeDemoList;
    }

    public List<RecipeDemo> findRecipe(Integer serveCapacity,String ingredients) throws RecipeNotFoundException {

        List<RecipeDemo> recipeDemoListSearch = recipeRepository.findAll();
        List<RecipeDemo> recipeDemos = new ArrayList<>();
        for(RecipeDemo recipeDemo:recipeDemoListSearch){

            if (recipeDemo.getServeCapacity()== serveCapacity && recipeDemo.getIngredients().contains(recipeDemo.getIngredients())) {
                recipeDemos.add(recipeDemo);
            }

        }
        return recipeDemos;
    }

}