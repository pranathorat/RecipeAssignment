package com.recipe.recipeAssignment.recipeDemo.entity;

import javax.persistence.*;
import lombok.*;

/**
 * RecipeDemo Entity : stores the recipes
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="recipedemo")
public class RecipeDemo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="recipeid")
    Integer recipeId;
    @Column(name="recipename")
    String recipeName;
    @Column(name="category")
    String category;
    @Column(name="servecapacity")
    Integer serveCapacity;
    @Column(name="ingredients")
    String ingredients;
    @Column(name="instructions")
    String instructions;

}