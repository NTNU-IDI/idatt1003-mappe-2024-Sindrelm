package edu.ntnu.iir.bidata;

import java.util.ArrayList;

public class FoodStorage {

  private ArrayList<Grocery> groceries;

  public FoodStorage(ArrayList<Grocery> groceries) {
    this.groceries = groceries;
  }

  public void addGrocery(Grocery grocery) {
    if (!groceryExists(grocery.getFoodName())) {
      groceries.add(grocery);
    } else {
      int i = getGroceryInd(grocery);
      groceries.get(i).addAmount(grocery.getAmount());
    }
  }

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
