package com.recipe.recipeAssignment.recipeDemo.controller;


import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.exceptionHandler.RecipeNotFoundException;
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

    @ResponseStatus(HttpStatus.OK)
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


    @ResponseStatus( HttpStatus.CREATED )
    @PostMapping(value = "/recipes")
    public RecipeDemo addRecipe(@Valid @RequestBody RecipeDemo recipe) {
        RecipeDemo recipeDemo= recipeService.addRecipe(recipe);
        log.info("Recipe Added SuccessFully");
        return recipeDemo;
    }

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

    @DeleteMapping(value = "/recipes/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable Integer recipeId) throws RecipeIdNotExistException {

        recipeService.deleteRecipe(recipeId);
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

    @Operation(summary="Get Recipes", description="Get Recipe by Serving capacity and ingredient", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe found by serve Capacity",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not available as Per Serve Count and ingredient",content = @Content)
    })
    @GetMapping("/recipe/search/{serveCapacity}/{ingredients}")
    public ResponseEntity <List<RecipeDemo>> findRecipe (@PathVariable("serveCapacity") Integer serveCapacity,@PathVariable("ingredients") String ingredients) throws Exception{
      List<RecipeDemo> recipeDemoList = recipeService.findRecipe(serveCapacity,ingredients);
        log.info("{}",recipeDemoList);
        return ResponseEntity.ok(recipeDemoList);
    }

    @Operation(summary="Get Recipes", description="Get Recipe by include/exclude ingredient and instruction", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe found by include/exclude ingredient and instruction",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not available as Per include/exclude ingredient and instruction",content = @Content)
    })

    @GetMapping("/searchRecipe/{ingredients}/{instructions}")
    public ResponseEntity <List<RecipeDemo>> findRecipeByInstructionIngredient (@PathVariable("ingredients") String ingredients,@PathVariable("instructions") String instructions) throws Exception{
        List<RecipeDemo> recipeDemoList = recipeService.findRecipeByInstructionIngredient(ingredients,instructions);
        log.info("{}",recipeDemoList);
        return ResponseEntity.ok(recipeDemoList);
    }

}

