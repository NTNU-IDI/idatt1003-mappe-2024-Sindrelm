package edu.ntnu.idi.idatt;

import java.util.ArrayList;

/**
 * Represents a cookbook containing a collection of recipes.
 */
public class CookBook {

  private final ArrayList<Recipe> recipes;

  /**
   * Constructs a new CookBook.
   *
   * @param recipes the list of recipes to be included in the cookbook
   */
  public CookBook(ArrayList<Recipe> recipes) {
    this.recipes = recipes;
  }

  /**
   * Gets the index of a recipe based on its short description.
   *
   * @param shortDescription the short description of the recipe
   * @return the index of the recipe
   * @throws IllegalArgumentException if the recipe is not found
   */
  public int getRecipeIndex(String shortDescription) {
    int i = 0;
    while (i < recipes.size()) {
      if (recipes.get(i).getShortDescription().equals(shortDescription)) {
        return i;
      }
      i++;
    }
    throw new IllegalArgumentException("Recipe not found in Cook Book");
  }

  /**
   * Gets a recipe based on its short description.
   *
   * @param shortDescription the short description of the recipe
   * @return the recipe with the specified short description
   * @throws IllegalArgumentException if the recipe is not found
   */
  public Recipe getRecipe(String shortDescription) {
    int recipeIndex = getRecipeIndex(shortDescription);
    return recipes.get(recipeIndex);
  }

  /**
   * Gets a register of all recipes in the cookbook.
   *
   * @return a string containing the short descriptions of all recipes, each on a new line
   */
  public String getCookBookRegister() {
    StringBuilder cookBookRegister = new StringBuilder();
    for (Recipe recipe : recipes) {
      cookBookRegister.append(recipe.getShortDescription()).append("\n");
    }
    return cookBookRegister.toString();
  }

  /**
   * Gets recipe suggestions based on the available groceries in the food storage.
   *
   * @param foodStorage the food storage to check against
   * @return a string containing the short descriptions of the recipes that can be made with the
   * available groceries, each on a new line
   */
  public String getRecipeSuggestions(FoodStorage foodStorage) {
    StringBuilder recipeSuggestions = new StringBuilder();
    int i = 0;
    while (i < recipes.size()) {
      if (recipes.get(i).checkFoodStorage(foodStorage)) {
        recipeSuggestions.append(recipes.get(i).getShortDescription()).append("\n");
      }
      i++;
    }
    return recipeSuggestions.toString();
  }

  /**
   * Adds a new recipe to the cookbook.
   *
   * @param recipe the recipe to be added
   */
  public void addRecipe(Recipe recipe) {
    this.recipes.add(recipe);
  }
}