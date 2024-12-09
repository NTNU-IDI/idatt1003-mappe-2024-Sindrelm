package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Grocery;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for the FoodStorage class.
 */
class FoodStorageTest {

  private static FoodStorage foodStorage;

  @BeforeAll
  static void setUp() {
    // Arrange shared resources
    foodStorage = new FoodStorage(new ArrayList<>());
    foodStorage.addGrocery("Flour", "kg", 1.0, 20.0, "20.12.2024");
    foodStorage.addGrocery("Milk", "l", 1.0, 15.0, "20.12.2024");
    foodStorage.addGrocery("Eggs", "kg", 0.5, 50.0, "20.12.2024");
  }

  @Test
  void testConstructorWithEmptyGroceries() {
    // Act
    FoodStorage emptyStorage = new FoodStorage(new ArrayList<>());
    // Assert
    assertEquals(0, emptyStorage.getAlphabeticallySortedGroceries().size());
  }

  @Test
  void testGetGrocery() {
    // Act
    Grocery milk = foodStorage.getGrocery("Milk");
    // Assert
    assertEquals("Milk", milk.getName());
    assertEquals("l", milk.getUnit());
    assertEquals(1.0, milk.getAmount());
    assertEquals(15.0, milk.getTotalPrice());
  }

  @Test
  void testGetGroceryThrowsException() {
    // Assert
    assertThrows(NoSuchElementException.class, () -> foodStorage.getGrocery("Sugar"));
  }

  @Test
  void testAddGrocery() {
    // Act
    foodStorage.addGrocery("Sugar", "kg", 0.5, 10.0, "20.12.2025");
    Grocery sugar = foodStorage.getGrocery("Sugar");
    // Assert
    assertEquals(0.5, sugar.getAmount());
  }

  @Test
  void testAddGroceryIncreasesAmount() {
    //arrange
    setUp();
    // Act
    foodStorage.addGrocery("Milk", "l", 0.5, 15.0, "20.12.2024");
    Grocery milk = foodStorage.getGrocery("Milk");
    // Assert
    assertEquals(1.5, milk.getAmount());
  }

  @Test
  void testRemoveGrocery() {
    //arrange
    setUp();
    // Act
    foodStorage.removeGrocery("Eggs");
    // Assert
    assertThrows(NoSuchElementException.class, () -> foodStorage.getGrocery("Eggs"));
  }

  @Test
  void testRemoveGroceryAmount() {
    //arrange
    setUp();
    // Act
    foodStorage.removeGroceryAmount("Milk", 0.5);
    Grocery milk = foodStorage.getGrocery("Milk");
    // Assert
    assertEquals(0.5, milk.getAmount());
  }

  @Test
  void testRemoveGroceryAmountRemovesGroceryWhenAmountIsZero() {
    //arrange
    setUp();
    // Act
    foodStorage.removeGroceryAmount("Milk", 1.0);
    // Assert
    assertThrows(NoSuchElementException.class, () -> foodStorage.getGrocery("Milk"));
  }

  @Test
  void testGetExpiredGroceries() {
    //Arrange
    setUp();
    // Act
    ArrayList<Grocery> expired = foodStorage.getExpiredGroceries();
    // Assert
    assertTrue(expired.isEmpty());
  }

  @Test
  void testGetGroceriesExpiringBefore() {
    //arrange
    setUp();
    // Act
    ArrayList<Grocery> expiringSoon = foodStorage.getGroceriesExpiringBefore("20.12.2025");
    // Assert
    assertEquals(3, expiringSoon.size());
  }

  @Test
  void testGetAlphabeticallySortedGroceries() {
    //arrange
    setUp();
    // Act
    ArrayList<Grocery> sortedGroceries = foodStorage.getAlphabeticallySortedGroceries();
    // Assert
    assertEquals("Eggs", sortedGroceries.get(0).getName());
    assertEquals("Flour", sortedGroceries.get(1).getName());
    assertEquals("Milk", sortedGroceries.get(2).getName());
  }

  @Test
  void testGroceryExists() {
    // Act
    boolean exists = foodStorage.groceryExists("Milk");
    // Assert
    assertTrue(exists);
  }

  @Test
  void testGroceryDoesNotExist() {
    // Act
    boolean exists = foodStorage.groceryExists("Sugar");
    // Assert
    assertFalse(exists);
  }
}
