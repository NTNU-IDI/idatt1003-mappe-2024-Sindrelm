package edu.ntnu.iir.bidata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class FoodStorageTest {

  @Test
  void testAddGrocery() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    assertTrue(foodStorage.groceryExists("Apple"));
  }

  @Test
  void testGroceryNotExists() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    assertFalse(foodStorage.groceryExists("Banana"));
  }

  @Test
  void testGetGroceryInd() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    assertEquals(0, foodStorage.getGroceryInd(grocery.getFoodName()));
  }

  @Test
  void testAddGroceryExisting() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    Grocery grocery2 = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.addGrocery(grocery2);
    assertEquals(2.0, grocery.getAmount());
  }

  @Test
  void testGetGrocery() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    assertEquals(grocery, foodStorage.getGrocery("Apple"));
  }

  @Test
  void testRemoveGroceryAmount() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    foodStorage.removeGroceryAmount("Apple", 1.0);
    assertEquals(0.0, grocery.getAmount());
  }

  @Test
  void testRemoveGroceryAmountNotExists() {
    Grocery grocery = new Grocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery(grocery);
    assertThrows(IllegalArgumentException.class,
        () -> foodStorage.removeGroceryAmount("Banana", 1.0));
  }
}
