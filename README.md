# Project RecipeAssignment:

Create a standalone java application which allows users to manage their favourite recipes. It should 
allow adding, updating, removing and fetching recipes. Additionally users should be able to filter 
available recipes based on one or more of the following criteria: 

1. Whether or not the dish is vegetarian
2. The number of servings
3. Specific ingredients (either include or exclude)
4. Text search within the instructions.

For example, the API should be able to handle the following search requests: 

• All vegetarian recipes 
• Recipes that can serve 4 persons and have “potatoes” as an ingredient 
• Recipes without “salmon” as an ingredient that has “oven” in the instructions

# How to build and run:
Build Spring Boot Project with Maven
clone the project
cd recipeDemo
mvn install

#Run Spring Boot app using Intellij

Endpoint URL:
http://localhost:8700

# API Documentation:

a friendly overview and documentation of this REST API will be available at:

http://localhost:8700/swagger-ui/index.html
