package com.recipe.recipeAssignment.recipeDemo;

import org.junit.runner.RunWith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@EnableAutoConfiguration
@SpringBootApplication
public class RecipeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeDemoApplication.class, args);
	}

}
