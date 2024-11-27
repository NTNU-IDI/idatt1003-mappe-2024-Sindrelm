package edu.ntnu.idi.idatt.modules;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Represents a recipe with a list of groceries, a short description, a method, and the number of
 * portions.
 */
public class Recipe {

  private final ArrayList<Grocery> groceries;
  private String shortDescription;
  private String method;
  private int nrOfPortions;

  /**
   * Constructs a new Recipe.
   *
   * @param groceries        the list of groceries required for the recipe
   * @param shortDescription a short description of the recipe
   * @param method           the method or instructions to prepare the recipe
   * @param nrOfPortions     the number of nrOfPortions the recipe yields
   * @throws IllegalArgumentException if any of the parameters are null or nrOfPortions is less than
   *                                  1
   */
  public Recipe(ArrayList<Grocery> groceries, String shortDescription, String method,
      int nrOfPortions) {
    if (groceries == null || shortDescription == null || method == null || nrOfPortions < 1) {
      throw new IllegalArgumentException(
          "Groceries, short description, nrOfPortions and method must be non-null");
    }
    this.groceries = groceries;
    this.shortDescription = shortDescription;
    this.method = method;
    this.nrOfPortions = nrOfPortions;
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
   * Updates the short description of the recipe.
   *
   * @param shortDescription the new short description
   */
  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
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
   * Updates the method or instructions to prepare the recipe.
   *
   * @param method the new method
   */
  public void setMethod(String method) {
    this.method = method;
  }

  /**
   * Gets the number of portions the recipe yields.
   *
   * @return the number of portions
   */
  public int getNrOfPortions() {
    return nrOfPortions;
  }

  /**
   * Calculates the total price of the recipe based on the prices of the groceries.
   *
   * @return the total price of the recipe
   */
  public double getRecipePrice() {
    return groceries.stream()
        .mapToDouble(grocery -> grocery.getPrice() * grocery.getAmount())
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
    recipeInformation.append("Number of portions: ").append(nrOfPortions).append("\n \n");
    recipeInformation.append("Groceries: \n");
    for (Grocery grocery : groceries) {
      recipeInformation.append(grocery.getFoodName()).append(", ");
      recipeInformation.append(grocery.getAmount());
      recipeInformation.append(grocery.getUnit()).append(", ");
      recipeInformation.append(grocery.getPrice()).append("kr \n");
    }
    recipeInformation.append("\nTotal price: ");
    recipeInformation.append(getRecipePrice()).append("kr \n");
    return recipeInformation.toString();
  }

  /**
   * Adds a grocery item to the recipe.
   *
   * @param foodName the name of the food item
   * @param siUnit   the SI unit of the food item
   * @param amount   the amount of the food item
   * @param price    the price of the food item
   */
  public void addGrocery(String foodName, String siUnit, double amount, double price) {
    groceries.add(new Grocery(foodName, siUnit, amount, price, "11.11.1111"));
  }

  /**
   * Updates the number of portions the recipe yields.
   *
   * @param nrOfPortions the new number of portions
   */
  public void setNumberOfPortions(int nrOfPortions) {
    this.nrOfPortions = nrOfPortions;
  }

  /**
   * Checks if the required groceries for the recipe are available in the food storage.
   *
   * @param foodStorage the food storage to check against
   * @return true if all required groceries are available in sufficient amounts, false otherwise
   */
  public boolean checkFoodStorage(FoodStorage foodStorage) {
    for (Grocery grocery : groceries) {
      try {
        double recipeAmount = grocery.getAmount();
        double foodStorageAmount = foodStorage.getGrocery(grocery.getFoodName()).getAmount();
        double amountDifference = foodStorageAmount - recipeAmount;
        if (amountDifference < 0) {
          return false;
        }
      } catch (NoSuchElementException e) {
        return false;
      }
    }
    return true;
  }

}