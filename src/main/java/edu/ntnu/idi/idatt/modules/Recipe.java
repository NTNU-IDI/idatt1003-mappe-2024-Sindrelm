package edu.ntnu.idi.idatt.modules;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents a recipe with its details.
 * <p>
 * The Recipe class represents a recipe which contains a list of groceries, a short description, a
 * method, and the number of portions the recipe yields. The Recipes created is stored in the
 * CookBook class
 *
 * @author Sindre Larsen Mjøs
 * @version 2.0
 * @since 01.11.2024
 */
public class Recipe {

  /**
   * Variables
   */
  private final ArrayList<Grocery> groceries;
  private final String shortDescription;
  private final String method;
  private final int numberOfPortions;

  /**
   * Constructs a new Recipe.
   *
   * @param groceries        the list of groceries required for the recipe
   * @param shortDescription a short description of the recipe
   * @param method           the method or instructions to prepare the recipe
   * @param numberOfPortions the number of portions the recipe yields
   */
  public Recipe(ArrayList<Grocery> groceries, String shortDescription, String method,
      int numberOfPortions) {

    verifyGroceries(groceries);
    verifyShortDescription(shortDescription);
    verifyMethod(method);
    verifyNumberOfPortions(numberOfPortions);

    this.groceries = groceries;
    this.shortDescription = shortDescription;
    this.method = method;
    this.numberOfPortions = numberOfPortions;
  }

  /**
   * Verifies that the groceries list is not null.
   *
   * @param groceries the list of groceries to verify
   */
  public static void verifyGroceries(ArrayList<Grocery> groceries) {
    if (groceries == null) {
      throw new IllegalArgumentException(
          "Groceries can not be null");
    }
  }

  /**
   * Verifies that the short description is not empty.
   *
   * @param shortDescription the short description to verify
   */
  public static void verifyShortDescription(String shortDescription) {
    if (shortDescription.isBlank()) {
      throw new IllegalArgumentException(
          "Short description can not be empty");
    }
  }

  /**
   * Verifies that the method is not empty.
   *
   * @param method the method to verify
   */
  public static void verifyMethod(String method) {
    if (method.isBlank()) {
      throw new IllegalArgumentException(
          "Method can not be empty");
    }
  }

  /**
   * Verifies that the number of portions is at least 1.
   *
   * @param numberOfPortions the number of portions to verify
   */
  public static void verifyNumberOfPortions(int numberOfPortions) {
    if (numberOfPortions < 1) {
      throw new IllegalArgumentException(
          "Number of portions must be at least 1");
    }
  }


  /**
   * Gets the list of groceries required for the recipe.
   *
   * @return the list of groceries
   */
  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  /**
   * Gets the short description of the recipe.
   *
   * @return the short description
   */
  public String getShortDescription() {
    return shortDescription;
  }

  /**
   * Gets the method or instructions to prepare the recipe.
   *
   * @return the method
   */
  public String getMethod() {
    return method;
  }

  /**
   * Gets the number of portions the recipe yields.
   *
   * @return the number of portions
   */
  public int getNumberOfPortions() {
    return numberOfPortions;
  }

  /**
   * Calculates the total price of the recipe based on the prices of the groceries.
   *
   * @return the total price of the recipe
   */
  public double getRecipePrice() {
    return groceries.stream()
        .mapToDouble(Grocery::getTotalPrice)
        .sum();
  }


  /**
   * Gets the detailed information of the recipe.
   *
   * @return a string containing the detailed information of the recipe
   */
  public String getRecipeInformation() {
    StringBuilder recipeInformation = new StringBuilder();
    recipeInformation.append("Recipe: ").append(shortDescription).append("\n");
    recipeInformation.append("Method: ").append(method).append("\n");
    recipeInformation.append("Number of portions: ").append(numberOfPortions).append("\n \n");
    recipeInformation.append("Groceries: \n");
    for (Grocery grocery : groceries) {
      recipeInformation.append(grocery.getName()).append(", ");
      recipeInformation.append(grocery.getAmount());
      recipeInformation.append(grocery.getUnit()).append(", ");
      recipeInformation.append(grocery.getTotalPrice()).append("kr \n");
    }
    recipeInformation.append("\nTotal price: ");
    recipeInformation.append(getRecipePrice()).append("kr \n");
    return recipeInformation.toString();
  }

  /**
   * Adds a grocery item to the recipe.
   *
   * @param foodName the name of the food item
   * @param unit     the unit of the food item (kg or l)
   * @param amount   the amount of the food item
   * @param price    the price of the food item
   */
  public void addGrocery(String foodName, String unit, double amount, double price) {
    groceries.add(new Grocery(foodName, unit, amount, price, "21.02.1848"));
  }

  /**
   * Checks if the required groceries for the recipe are available in the food storage.
   *
   * @param foodStorage the food storage to check against
   * @return true if all required groceries are available in sufficient amounts, false otherwise
   * @throws NoSuchElementException if the grocery is not found in the food storage
   */
  public boolean checkFoodStorage(FoodStorage foodStorage) {
    return groceries.stream().allMatch(grocery -> {
      try {
        return foodStorage.getGrocery(grocery.getName()).getAmount() >= grocery.getAmount();
      } catch (NoSuchElementException e) {
        return false;
      }
    });
  }
}