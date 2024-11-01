package edu.ntnu.iir.bidata;

import java.util.ArrayList;

/**
 * Represents a recipe with a list of groceries, a short description, a method, and the number of
 * portions.
 */
public class Recipe {

  private ArrayList<Grocery> groceries;
  private String shortDescription;
  private String method;
  private int portions;

  /**
   * Constructs a new Recipe.
   *
   * @param groceries        the list of groceries required for the recipe
   * @param shortDescription a short description of the recipe
   * @param method           the method or instructions to prepare the recipe
   * @param portions         the number of portions the recipe yields
   * @throws IllegalArgumentException if any of the parameters are null or portions is less than 1
   */
  public Recipe(ArrayList<Grocery> groceries, String shortDescription, String method,
      int portions) {
    if (groceries == null || shortDescription == null || method == null || portions < 1) {
      throw new IllegalArgumentException(
          "Groceries, short description, portions and method must be non-null");
    }
    this.groceries = groceries;
    this.shortDescription = shortDescription;
    this.method = method;
    this.portions = portions;
  }

  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  public void addGrocery(String foodName, String siUnit, double amount, double price) {
    groceries.add(new Grocery(foodName, siUnit, amount, price, "00.00.0000"));
  }

  public boolean checkFoodStorage(FoodStorage foodStorage) {
    int i = 0;
    while (i < groceries.size()) {
      try {
        double recipeAmount = groceries.get(i).getAmount();
        double foodStorageAmount = foodStorage.getGrocery(groceries.get(i).getFoodName())
            .getAmount();
        double amountDifference = foodStorageAmount - recipeAmount;
        if (amountDifference < 0) {
          return false;
        } else if (amountDifference >= 0) {
          i++;
        }
      } catch (IllegalArgumentException e) {
        return false;
      }
    }
    return true;
  }
}