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
   * Adds a grocery item to the storage. If the grocery item already exists, it adds the specified
   * amount to the existing item.
   *
   * @param grocery the grocery item to be added
   */
  public void addGrocery(Grocery grocery) {
    if (!groceryExists(grocery.getFoodName())) {
      groceries.add(grocery);
    } else {
      int i = getGroceryInd(grocery);
      groceries.get(i).addAmount(grocery.getAmount());
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
   * @param grocery the grocery item to find
   * @return the index of the grocery item, or -1 if not found
   */
  public int getGroceryInd(Grocery grocery) {
    String foodName = grocery.getFoodName();
    int i = 0;
    while (i < groceries.size()) {
      if (groceries.get(i).getFoodName().equals(foodName)) {
        return i;
      }
      i++;
    }
    return -1;
  }
}