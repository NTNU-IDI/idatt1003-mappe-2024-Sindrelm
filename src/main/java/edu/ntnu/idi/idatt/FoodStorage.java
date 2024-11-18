package edu.ntnu.idi.idatt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

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
      int groceryIndex = getGroceryIndex(foodName);
      groceries.get(groceryIndex).addAmount(amount);
    }
  }

  /**
   * Removes a grocery item from the storage by its food name.
   *
   * @param foodName the name of the food item to remove
   * @throws IllegalArgumentException if the grocery item does not exist
   */
  public void removeGrocery(String foodName) {
    int groceryIndex = getGroceryIndex(foodName);
    groceries.remove(groceryIndex);
  }

  /**
   * Removes a specified amount of a grocery item from the storage. If the resulting amount is less
   * than or equal to zero, the grocery item is removed from the storage.
   *
   * @param foodName the name of the food item to remove
   * @param amount   the amount of the food item to remove
   * @throws IllegalArgumentException if the grocery item does not exist
   */
  public void removeGroceryAmount(String foodName, double amount) {
    if (amount < 0) {
      throw new IllegalArgumentException("Amount to remove must be positive");
    }
    if (groceryExists(foodName)) {
      Grocery grocery = getGrocery(foodName);
      grocery.addAmount(-amount);
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
   * @return the index of the grocery item
   * @throws IllegalArgumentException if the grocery item does not exist
   */
  public int getGroceryIndex(String foodName) {
    int i = 0;
    while (i < groceries.size()) {
      if (groceries.get(i).getFoodName().equals(foodName)) {
        return i;
      }
      i++;
    }
    throw new IllegalArgumentException("Grocery not found in Food Storage");
  }

  /**
   * Gets a list of grocery items that have expired.
   *
   * @return a list of grocery items that have expired
   */
  public ArrayList<Grocery> getExpiredGroceries() {
    ArrayList<Grocery> expiredGroceries = new ArrayList<Grocery>();
    int i = 0;
    while (i < groceries.size()) {
      if (groceries.get(i).isExpired()) {
        expiredGroceries.add(groceries.get(i));
      }
      i++;
    }
    return expiredGroceries;
  }

  /**
   * Gets a list of grocery items that expire before the specified date.
   *
   * @param date the date to compare with
   * @return a list of grocery items that expire before the specified date
   * @throws ParseException if the date is not in the correct format
   */
  public ArrayList<Grocery> getExpireBefore(String date) throws ParseException {
    ArrayList<Grocery> expireBefore = new ArrayList<Grocery>();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Date expireBeforeDate = dateFormat.parse(date);
    int i = 0;
    while (i < groceries.size()) {
      if (expireBeforeDate.after(groceries.get(i).getExpirationDate())) {
        expireBefore.add(groceries.get(i));
      }
      i++;
    }
    return expireBefore;
  }

  /**
   * Sorts the groceries list alphabetically by their food name and returns the sorted list.
   *
   * @return the sorted list of grocery items
   */
  public ArrayList<Grocery> getSortedGroceries() {
    groceries.sort(Comparator.comparing(Grocery::getFoodName));
    return groceries;
  }
}

