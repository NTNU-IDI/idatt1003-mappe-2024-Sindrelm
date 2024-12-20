package edu.ntnu.idi.idatt.modules;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents a Cook book to store Recipe items. The class contains a list of Recipe items from the
 * Recipe class.
 *
 * @author Sindre Larsen Mjøs
 * @version 2.0
 * @since 01.11.2024
 */
public class CookBook {

  /**
   * Variables
   */
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
   * @throws NoSuchElementException if the recipe is not found
   */
  public int getRecipeIndex(String shortDescription) {
    for (int recipeIndex = 0; recipeIndex < recipes.size(); recipeIndex++) {
      if (recipes.get(recipeIndex).getShortDescription().equals(shortDescription)) {
        return recipeIndex;
      }
    }
    throw new NoSuchElementException("Recipe not found in CookBook");
  }

  /**
   * Gets a recipe based on its short description.
   *
   * @param shortDescription the short description of the recipe
   * @return the recipe with the specified short description
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
    cookBookRegister.append("CookBook Register:\n");
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
    recipeSuggestions.append(
        "Recipes you can make with current ingredients in the food storage:\n");
    for (Recipe recipe : recipes) {
      if (recipe.checkFoodStorage(foodStorage)) {
        recipeSuggestions.append(recipe.getShortDescription()).append("\n");
      }
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