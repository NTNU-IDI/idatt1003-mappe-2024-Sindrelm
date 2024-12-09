package edu.ntnu.idi.idatt.modules;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Represents a storage for grocery items.
 */
public class FoodStorage {

  private final ArrayList<Grocery> groceries;

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
   * @return the grocery item with the specified food name
   */
  public Grocery getGrocery(String foodName) {
    int groceryIndex = getGroceryIndex(foodName);
    return groceries.get(groceryIndex);
  }

  /**
   * Gets the index of the grocery item in the storage.
   *
   * @param foodName the grocery item to find
   * @return the index of the grocery item
   * @throws IllegalArgumentException if the grocery item does not exist
   */
  public int getGroceryIndex(String foodName) {
    for (int groceryIndex = 0; groceryIndex < groceries.size(); groceryIndex++) {
      if (groceries.get(groceryIndex).getFoodName().equals(foodName)) {
        return groceryIndex;
      }
    }
    throw new NoSuchElementException("Grocery does not exist");
  }

  /**
   * Gets a list of grocery items that have expired.
   *
   * @return a list of grocery items that have expired
   */
  public ArrayList<Grocery> getExpiredGroceries() {
    return groceries.stream()
        .filter(Grocery::isExpired)
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Gets a list of grocery items that expire before the specified date.
   *
   * @param date the date to compare with
   * @return a list of grocery items that expire before the specified date
   */
  public ArrayList<Grocery> getGroceriesExpiringBefore(String date) {
    return groceries.stream()
        .filter(grocery -> grocery.expireBefore(date))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  /**
   * Sorts the groceries list alphabetically by their food name and returns the sorted list.
   *
   * @return the sorted list of grocery items
   */
  public ArrayList<Grocery> getAlphabeticallySortedGroceries() {
    groceries.sort(Comparator.comparing(
        Grocery::getFoodName));
    return groceries;
  }

  /**
   * Adds a grocery item to the storage. If the grocery item already exists, it adds the specified
   * amount to the existing item.
   *
   * @param name           the name of the food item to add
   * @param unit           the SI unit of the food item
   * @param amount         the amount of the food item
   * @param price          the price of the food item
   * @param expirationDate the expiration expirationDate of the food item
   */
  public void addGrocery(String name, String unit, double amount, double price,
      String expirationDate) {
    if (!groceryExists(name)) {
      groceries.add(new Grocery(name, unit, amount, price, expirationDate));
    } else {
      int groceryIndex = getGroceryIndex(name);
      groceries.get(groceryIndex).addAmount(amount);
    }
  }

  /**
   * Removes a grocery item from the storage by its food name.
   *
   * @param name of the food item to remove
   * @throws IllegalArgumentException if the grocery item does not exist
   */
  public void removeGrocery(String name) {
    int groceryIndex = getGroceryIndex(name);
    groceries.remove(groceryIndex);
  }

  /**
   * Removes a specified amount of a grocery item from the storage. If the resulting amount is less
   * than or equal to zero, the grocery item is removed from the storage.
   *
   * @param name   the name of the food item to remove
   * @param amount the amount of the food item to remove
   * @throws IllegalArgumentException if the grocery item does not exist
   */
  public void removeGroceryAmount(String name, double amount) {
    Grocery grocery = getGrocery(name);
    grocery.removeAmount(amount);
    if (grocery.getAmount() == 0) {
      removeGrocery(name);
    }
  }

  /**
   * Checks if a grocery item with the specified food name exists in the storage.
   *
   * @param foodName the name of the food item to check
   * @return true if the grocery item exists, false otherwise
   */
  public boolean groceryExists(String foodName) {
    for (Grocery grocery : groceries) {
      if (foodName.equals(grocery.getFoodName())) {
        return true;
      }
    }
    return false;
  }
}