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

  public ArrayList<Grocery> getGroceries() {
    return groceries;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public String getMethod() {
    return method;
  }

  public int getNrOfPortions() {
    return nrOfPortions;
  }

  public void addGrocery(String foodName, String siUnit, double amount, double price) {
    groceries.add(new Grocery(foodName, siUnit, amount, price, "00.00.0000"));
  }

  public void addShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public void addMethod(String method) {
    this.method = method;
  }

  public void addNumberOfPortions(int nrOfPortions) {
    this.nrOfPortions = nrOfPortions;
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