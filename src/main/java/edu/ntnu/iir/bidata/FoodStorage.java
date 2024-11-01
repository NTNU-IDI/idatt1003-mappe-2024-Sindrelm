package edu.ntnu.iir.bidata;

import java.util.ArrayList;

/**
 * Represents a storage for grocery items.
 */
public class FoodStorage {

  private ArrayList<Grocery> groceries;

  /**
   * Constructs a new FoodStorage with the specified list of groceries.
   *
   * @param groceries the list of groceries to be stored
   */
  public FoodStorage(ArrayList<Grocery> groceries) {
    this.groceries = groceries;
  }

  /**
   * Retrieves a grocery item from the storage by its food name.
   *
   * @param foodName the name of the food item to retrieve
   * @return the grocery item with the specified food name, or null if not found
   */
  public Grocery getGrocery(String foodName) {
    int i = getGroceryInd(foodName);
    if (i == -1) {
      return null;
    }
    return groceries.get(i);
  }

  /**
   * Adds a grocery item to the storage. If the grocery item already exists, it adds the specified
   * amount to the existing item.
   *
   * @param foodName the name of the food item to add
   * @param siUnit   the SI unit of the food item
   * @param amount   the amount of the food item
   * @param price    the price of the food item
   * @param date     the expiration date of the food item
   */
  public void addGrocery(String foodName, String siUnit, double amount, double price, String date) {
    if (!groceryExists(foodName)) {
      groceries.add(new Grocery(foodName, siUnit, amount, price, date));
    } else {
      int groceryIndex = getGroceryInd(foodName);
      groceries.get(groceryIndex).addAmount(amount);
    }
  }

  public void removeGrocery(String foodName) {
    int groceryIndex = getGroceryInd(foodName);
    groceries.remove(groceryIndex);
  }

  public void removeGroceryAmount(String foodName, double Amount) {
    if (groceryExists(foodName)) {
      Grocery grocery = getGrocery(foodName);
      grocery.addAmount(-Amount);
      if (grocery.getAmount() <= 0) {
        removeGrocery(foodName);
      }
    } else {
      throw new IllegalArgumentException("Grocery does not exist");
    }
  }

  /**
   * Checks if a grocery item with the specified food name exists in the storage.
   *
   * @param foodName the name of the food item to check
   * @return true if the grocery item exists, false otherwise
   */
  public boolean groceryExists(String foodName) {
    int i = 0;
    while (i < groceries.size()) {
      if (groceries.get(i).getFoodName().equals(foodName)) {
        return true;
      }
      i++;
    }
    return false;
  }

  /**
   * Gets the index of the grocery item in the storage.
   *
   * @param foodName the grocery item to find
   * @return the index of the grocery item, or -1 if not found
   */
  public int getGroceryInd(String foodName) {
    int i = 0;
    while (i < groceries.size()) {
      if (groceries.get(i).getFoodName().equals(foodName)) {
        return i;
      }
      i++;
    }
    throw new IllegalArgumentException("Grocery does not exist");
  }
}