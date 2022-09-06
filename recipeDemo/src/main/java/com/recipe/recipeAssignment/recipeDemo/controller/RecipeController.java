package com.recipe.recipeAssignment.recipeDemo.controller;

import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.service.RecipeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controller Layer implementation
 */

@Slf4j
@RestController
public class RecipeController {

    private RecipeServiceImpl recipeService;
    @Autowired
    Environment environment;

    /**
     * Constructor based dependency Injection
     * @param recipeService {@link RecipeServiceImpl}
     */
    public RecipeController(RecipeServiceImpl recipeService) {
        this.recipeService = recipeService;
    }


    @Operation(summary="Get Recipes", description="Get list of recipes", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Found the Recipe",
            content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipes not found",content = @Content)
    })

    /**
     * Fetches all recipe details
     */
    @GetMapping(value = "/recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() throws Exception {
        List<RecipeDto> recipeList = recipeService.getAllRecipes();
        log.info("Get all recipes");
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }

    /**
     * It will fetch Recipes by recipe Id
     * @param recipeId {@link Integer}
     * @return {@link ResponseEntity}
     * @throws RecipeNotFoundException
     */
    @Operation(summary="Get Recipes By Id", description="Get Recipe By Id", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Found the Recipe By Id",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not found By Id",content = @Content)
    })

    @GetMapping(value = "/recipe/{recipeId}")

    public ResponseEntity<RecipeDemo>getRecipe(@PathVariable Integer recipeId)throws RecipeNotFoundException{
        return ResponseEntity.ok(recipeService.getRecipe(recipeId));
    }


    /**
     * will create new Recipe
     * @param recipe {@link RecipeDto}
     * @return {@link ResponseEntity}wrapper with created status
     * @throws Exception
     */
    @Operation(summary="Add recipe", description="Recipe will be added here", tags ="Post")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe Added SuccessFully",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe Not Added",content = @Content)
    })

    @PostMapping(value = "/recipes")
   public ResponseEntity<String> addRecipe(@RequestBody @Valid RecipeDto recipe) throws Exception {
        RecipeDemo recipeId = recipeService.addRecipe(recipe);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS");//+ recipeId;
        log.info("Recipe Added SuccessFully");
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    //Delete Recipe By RecipeID

    /**
     * Recipes will be deleted by Recipe Id
     * @param recipeId {@link Integer}
     * @return {@link ResponseEntity} wrapper with ok status
     * @throws RecipeIdNotExistException
     */
    @Operation(summary="Delete Recipe", description="Delete existing recipe", tags ="Delete")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe Deleted Successfully",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe Id Not available For Delete Operation",content = @Content)
    })
    //recipe will delete By recipe Id
    @DeleteMapping(value = "/recipes/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Integer recipeId) throws RecipeIdNotExistException {

        recipeService.deleteRecipe(recipeId);
       // log.info("Recipe Deleted");
        String successMessage = environment.getProperty("API.DELETE_SUCCESS");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }


    /**
     * recipe will get updated using recipe id
     * @param recipeId {@link Integer}
     * @param recipe {@link RecipeDto}
     * @return {@link ResponseEntity}wrapper with Ok Status
     * @throws Exception
     */
    @Operation(summary="Update Recipe", description="Update Existing recipe", tags ="Put")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe Updated",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not updated",content = @Content)
    })

      @PutMapping(value = "/recipes/{recipeId}")
        public ResponseEntity<String> updateRecipe(@PathVariable Integer recipeId, @RequestBody RecipeDto recipe)
            throws Exception {
        RecipeDto recipe1 = RecipeDto.valueOf(recipeService.getRecipe(recipeId));
        recipe1 = recipe;
        final RecipeDemo updateRecipe = recipeService.updateRecipe(recipe1);
        String successMessage = environment.getProperty("API.UPDATE_SUCCESS");
        log.info("Update recipe");
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    /**
     * Fetches recipes By Category i.e Veg or non-veg
     * @param category {@link String}
     * @return {@link ResponseEntity} with ok status
     * @throws Exception
     */

    @Operation(summary="Get Recipes", description="Get Recipe by Category", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe found by category",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Category Not Found",content = @Content)
    })

   @GetMapping(value = "/recipesBy/{category}")
    public ResponseEntity<List<RecipeDemo>>getByCategory(@PathVariable String category) throws Exception {
        return new ResponseEntity<List<RecipeDemo>>(recipeService.getByCategory(category), HttpStatus.OK);
    }

    /**
     * Fetches Recipe By serving capacity count
     * @param serveCapacity {@link Integer}
     * @return {@link ResponseEntity} with {@link List}of {@link RecipeDemo}
     * @throws Exception
     */

    @Operation(summary="Get Recipes", description="Get Recipe by Serving capacity", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe found by serve Capacity",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not available as Per Serve Count",content = @Content)
    })

    @GetMapping(value="/recipeServeFor/{serveCapacity}")
    public ResponseEntity<List<RecipeDemo>>getByServeCapacity(@PathVariable Integer serveCapacity)throws Exception{
        return new ResponseEntity<List<RecipeDemo>>(recipeService.getByServeCapacity(serveCapacity),HttpStatus.OK);
    }

    /**
     * Fetches Recipe by serve count and ingredient present
     * @param serveCapacity {@link Integer}
     * @param ingredients {@link String}
     * @return {@link ResponseEntity} with {@link List}of {@link RecipeDemo}
     * @throws RecipeNotFoundException
     */
    @GetMapping(value = "/searchByServeCapacity/{serveCapacity}/searchByIncludeIngredients/{ingredients}")
    public ResponseEntity<List<RecipeDemo>> findAllRecipeByServingCapacityAndIngredient(@PathVariable Integer serveCapacity, @PathVariable String ingredients) throws RecipeNotFoundException {

        return ResponseEntity.ok(recipeService.findAllRecipeByServingCapacityAndIngredient(serveCapacity, ingredients));
    }

    /**
     * fetch Recipe by instructions and include or exclude ingredient
     * @param includeIngredient {@link boolean}
     * @param ingredients {@link Integer}
     * @param includeInstructions {@link boolean}
     * @param instructions {@link String}
     * @return {@link ResponseEntity} with {@link List}of {@link RecipeDemo}
     * @throws RecipeNotFoundException
     */
    @GetMapping(path = "/searchByInstructionsAndIngredient")
    public ResponseEntity<List<RecipeDemo>> findAllRecipeByIngredientAndInstruction(@RequestParam(defaultValue = "true") boolean includeIngredient,
                                                                                       @RequestParam String ingredients, @RequestParam(defaultValue = "true") boolean includeInstructions, @RequestParam String instructions) throws RecipeNotFoundException {

        return ResponseEntity.ok(recipeService.findAllRecipeByIngredientAndInstruction(includeIngredient, ingredients, includeInstructions, instructions));
    }
  }

