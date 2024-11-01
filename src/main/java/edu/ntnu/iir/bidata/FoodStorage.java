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
    return groceries.get(i);
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
      int i = getGroceryInd(grocery.getFoodName());
      groceries.get(i).addAmount(grocery.getAmount());
    }
  }

  public void removeGroceryAmount(String foodName, double Amount) {
    if (groceryExists(foodName)) {
      getGrocery(foodName).addAmount(-Amount);
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
    return -1;
  }
}