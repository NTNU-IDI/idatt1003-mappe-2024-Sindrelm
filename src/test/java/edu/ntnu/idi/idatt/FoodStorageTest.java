package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Grocery;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Test class for the FoodStorage class.
 */
public class FoodStorageTest {

  /**
   * Tests adding a grocery to the food storage.
   */
  @Test
  void testAddGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(foodStorage.groceryExists("Apple"));
  }

  /**
   * Tests that a grocery does not exist in the food storage.
   */
  @Test
  void testGroceryNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertFalse(foodStorage.groceryExists("Banana"));
  }

  /**
   * Tests getting the index of a grocery in the food storage.
   */
  @Test
  void testGetGroceryIndex() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertEquals(0, foodStorage.getGroceryIndex("Apple"));
  }

  /**
   * Tests adding an existing grocery to the food storage.
   */
  @Test
  void testAddGroceryExisting() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertEquals(2.0, foodStorage.getGrocery("Apple").getAmount());
  }

  /**
   * Tests getting a grocery from the food storage.
   */
  @Test
  void testGetGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertEquals("Apple", foodStorage.getGrocery("Apple").getFoodName());
  }

  /**
   * Tests removing an amount of a grocery from the food storage.
   */
  @Test
  void testRemoveGroceryAmount() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 2.0, 10.0, "19.02.2004");
    foodStorage.removeGroceryAmount("Apple", 1.0);
    assertEquals(1.0, foodStorage.getGrocery("Apple").getAmount());
  }

  /**
   * Tests removing an amount of a grocery that does not exist in the food storage.
   */
  @Test
  void testRemoveGroceryAmountNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertThrows(NoSuchElementException.class,
        () -> foodStorage.removeGroceryAmount("Banana", 1.0));
  }

  /**
   * Tests removing a grocery from the food storage.
   */
  @Test
  void testRemoveGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.removeGrocery("Apple");
    assertFalse(foodStorage.groceryExists("Apple"));
  }

  /**
   * Tests removing a grocery that does not exist in the food storage.
   */
  @Test
  void testRemoveGroceryNotExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertThrows(NoSuchElementException.class,
        () -> foodStorage.removeGrocery("Banana"));
  }

  @Test
  void testRemoveGroceryAmountNegative() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertThrows(IllegalArgumentException.class,
        () -> foodStorage.removeGroceryAmount("Apple", -1.0));
  }

  /**
   * Tests that a grocery exists in the food storage.
   */
  @Test
  void testGroceryExists() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    assertTrue(foodStorage.groceryExists("Apple"));
  }

  /**
   * Tests getting expired groceries from the food storage.
   */
  @Test
  void testGetExpiredGroceries() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2004");
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2026");
    assertEquals(1, foodStorage.getExpiredGroceries().size());
  }

  /**
   * Tests getting expired groceries from the food storage when there are no expired groceries.
   */
  @Test
  void testGetExpiredGroceriesEmpty() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2026");
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2026");
    assertEquals(0, foodStorage.getExpiredGroceries().size());
  }

  /**
   * Tests getting groceries that expire before a given date from the food storage.
   *
   * @throws ParseException if there is an error during parsing
   */
  @Test
  void testGetExpireBefore() throws ParseException {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2024");
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2024");
    assertEquals(2, foodStorage.getExpireBefore("19.02.2026").size());
  }

  /**
   * Tests getting sorted groceries from the food storage.
   */
  @Test
  void testGetAlphabeticallySortedGroceries() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Banana", "kg", 1.0, 10.0, "19.02.2024");
    foodStorage.addGrocery("Apple", "kg", 1.0, 10.0, "19.02.2024");
    assertEquals("Apple", foodStorage.getAlphabeticallySortedGroceries().get(0).getFoodName());
  }
}