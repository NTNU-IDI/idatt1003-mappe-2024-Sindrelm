package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
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
  void testGetGroceryIndex() {
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
    foodStorage.addGrocery("Apple", "kg", 2.0, 10.0, "19.02.2004");
    foodStorage.removeGroceryAmount("Apple", 1.0);
    assertEquals(1.0, foodStorage.getGrocery("Apple").getAmount());
  }

  @Test
  void testRemoveGroceryAmountNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertThrows(IllegalArgumentException.class,
        () -> foodStorage.removeGroceryAmount("Banana", 1.0));
  }

  @Test
  void testRemoveGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.removeGrocery("Apple");
    assertFalse(foodStorage.groceryExists("Apple"));
  }

  @Test
  void testRemoveGroceryNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    assertThrows(IllegalArgumentException.class,
        () -> foodStorage.removeGrocery("Banana"));
  }

  @Test
  void testGroceryExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(foodStorage.groceryExists("Apple"));
  }

  @Test
  void testGetExpiredGroceries() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2026");
    assertEquals(1, foodStorage.getExpiredGroceries().size());
  }

  @Test
  void testGetExpiredGroceriesEmpty() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2026");
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2026");
    assertEquals(0, foodStorage.getExpiredGroceries().size());
  }

  @Test
  void testGetExpireBefore() throws ParseException {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2024");
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2024");
    assertEquals(2, foodStorage.getExpireBefore("19.02.2026").size());
  }

  @Test
  void testGetSortedGroceries() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2024");
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2024");
    assertEquals("Apple", foodStorage.getSortedGroceries().get(0).getFoodName());
  }
}
