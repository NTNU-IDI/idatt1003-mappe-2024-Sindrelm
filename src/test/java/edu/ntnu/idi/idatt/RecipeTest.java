package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Grocery;
import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Recipe class.
 */
public class RecipeTest {

  /**
   * Tests that an exception is thrown when creating a Recipe with a null grocery list.
   */
  @Test
  void testRecipeThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Recipe(null, "shortDescription", "method", 1));
  }

  /**
   * Tests the getRecipePrice method.
   */
  @Test
  void testGetRecipePrice() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    Grocery grocery = new Grocery("apple", "kg", 1.0, 1.0, "01.01.2021");
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertEquals(recipe.getRecipePrice(), 1.0);
  }

  /**
   * Tests the addGrocery method.
   */
  @Test
  void testAddGrocery() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertEquals(recipe.getGroceries().size(), 1);
  }

  @Test
  void testAddGroceryThrowsException() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    assertThrows(IllegalArgumentException.class, () -> recipe.addGrocery(null, "kg", 1.0, 1.0));
    assertThrows(IllegalArgumentException.class, () -> recipe.addGrocery("apple", null, 1.0, 1.0));
    assertThrows(IllegalArgumentException.class, () -> recipe.addGrocery("apple", "kg", -1.0, 1.0));
    assertThrows(IllegalArgumentException.class, () -> recipe.addGrocery("apple", "kg", 1.0, -1.0));
  }

  /**
   * Tests the checkFoodStorage method when the food storage contains the required groceries.
   */
  @Test
  void testCheckFoodStorageTrue() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>());
    foodStorage.addGrocery("apple", "kg", 1.0, 1.0, "01.01.2021");
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertTrue(recipe.checkFoodStorage(foodStorage));
  }

  /**
   * Tests the checkFoodStorage method when the food storage does not contain enough of the required
   * groceries.
   */
  @Test
  void testCheckFoodStorageFalse() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>());
    foodStorage.addGrocery("apple", "kg", 1.0, 1.0, "01.01.2021");
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 2.0, 1.0);
    assertFalse(recipe.checkFoodStorage(foodStorage));
  }

  /**
   * Tests the checkFoodStorage method when the food storage does not contain the required
   * groceries.
   */
  @Test
  void testCheckFoodStorageForNonExistingGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>());
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertFalse(recipe.checkFoodStorage(foodStorage));
  }

  /**
   * Tests the addShortDescription method.
   */
  @Test
  void testSetShortDescription() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.setShortDescription("newShortDescription");
    assertEquals(recipe.getShortDescription(), "newShortDescription");
  }

  /**
   * Tests the addMethod method.
   */
  @Test
  void testAddMethod() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.setMethod("newMethod");
    assertEquals(recipe.getMethod(), "newMethod");
  }

  /**
   * Tests the addNumberOfPortions method.
   */
  @Test
  void testSetNumberOfPortions() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.setNumberOfPortions(2);
    assertEquals(recipe.getNrOfPortions(), 2);
  }
}