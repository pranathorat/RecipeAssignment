package com.recipe.recipeAssignment.recipeDemo.controller;

import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeIdNotExistException;
import com.recipe.recipeAssignment.recipeDemo.ExceptionHandler.RecipeNotFoundException;
import com.recipe.recipeAssignment.recipeDemo.dto.RecipeDto;
import com.recipe.recipeAssignment.recipeDemo.entity.RecipeDemo;
import com.recipe.recipeAssignment.recipeDemo.repository.RecipeRepository;
import com.recipe.recipeAssignment.recipeDemo.service.RecipeService;
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
@Slf4j
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    Environment environment;


    @Operation(summary="Get Recipes", description="Get list of recipes", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Found the Recipe",
            content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipes not found",content = @Content)
    })
    // Fetches all recipe details
    @GetMapping(value = "/recipes")
    public ResponseEntity<List<RecipeDto>> getAllRecipes() throws Exception {
        List<RecipeDto> recipeList = recipeService.getAllRecipes();
        log.info("Get all recipes");
        return new ResponseEntity<>(recipeList, HttpStatus.OK);
    }


    @Operation(summary="Get Recipes By Id", description="Get Recipe By Id", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Found the Recipe By Id",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not found By Id",content = @Content)
    })
    //Fetches recipe by recipe Id
    @GetMapping(value = "/recipe/{recipeId}")
    public ResponseEntity<RecipeDemo>getRecipe(@PathVariable Integer recipeId)throws RecipeNotFoundException{
        return ResponseEntity.ok(recipeService.getRecipe(recipeId));
    }



    @Operation(summary="Add recipe", description="Recipe will be added here", tags ="Post")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe Added SuccessFully",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe Not Added",content = @Content)
    })
    //Recipes will be added
    @PostMapping(value = "/recipes")
    public ResponseEntity<String> addRecipe(@RequestBody @Valid RecipeDto recipe) throws Exception {
         Integer recipeId = recipeService.addRecipe(recipe);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS") ;//+ recipeId;
        log.info("Recipe Added SuccessFully");
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }



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



    @Operation(summary="Update Recipe", description="Update Existing recipe", tags ="Put")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe Updated",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not updated",content = @Content)
    })
    //recipe will get updated using recipe id
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

    @Operation(summary="Get Recipes", description="Get Recipe by Category", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe found by category",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Category Not Found",content = @Content)
    })
    //Fetches recipes By Category
   @GetMapping(value = "/recipes/{category}")
    public ResponseEntity<List<RecipeDemo>> getByCategory(@PathVariable String category) throws Exception {
        //List<RecipeDto> recipeList = recipeService.findByCategory(category);
        return new ResponseEntity<List<RecipeDemo>>(recipeRepository.getByCategory(category), HttpStatus.OK);
    }



    @Operation(summary="Get Recipes", description="Get Recipe by Serving capacity", tags ="Get")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200",description = "Recipe found by serve Capacity",
                    content={@Content(mediaType = "application/json",schema = @Schema(implementation = RecipeDto.class))}),
            @ApiResponse(responseCode = "404",description = "Recipe not available as Per Serve Count",content = @Content)
    })

    //Fetches Recipe By serving capacity count
    @GetMapping(value="/recipeServeFor/{serveCapacity}")
    public ResponseEntity<List<RecipeDemo>>getByServeCapacity(@PathVariable Integer serveCapacity)throws Exception{
        return new ResponseEntity<List<RecipeDemo>>(recipeRepository.getByServeCapacity(serveCapacity),HttpStatus.OK);
    }


}

