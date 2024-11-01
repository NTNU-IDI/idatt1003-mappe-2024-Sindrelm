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
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(foodStorage.groceryExists("Apple"));
  }

  @Test
  void testGroceryNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertFalse(foodStorage.groceryExists("Banana"));
  }

  @Test
  void testGetGroceryInd() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertEquals(0, foodStorage.getGroceryInd("Apple"));
  }

  @Test
  void testAddGroceryExisting() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertEquals(2.0, foodStorage.getGrocery("Apple").getAmount());
  }

  @Test
  void testGetGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertEquals("Apple", foodStorage.getGrocery("Apple").getFoodName());
  }

  @Test
  void testRemoveGroceryAmount() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.removeGroceryAmount("Apple", 1.0);
    assertEquals(0.0, foodStorage.getGrocery("Apple").getAmount());
  }

  @Test
  void testRemoveGroceryAmountNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertThrows(IllegalArgumentException.class,
        () -> foodStorage.removeGroceryAmount("Banana", 1.0));
  }
}
