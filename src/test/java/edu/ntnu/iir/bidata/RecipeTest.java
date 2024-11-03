package edu.ntnu.iir.bidata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class RecipeTest {

  @Test
  void testRecipeThrowsException() {
    assertThrows(IllegalArgumentException.class,
        () -> new Recipe(null, "shortDescription", "method", 1));
  }

  @Test
  void testAddGrocery() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertEquals(recipe.getGroceries().size(), 1);
  }

  @Test
  void testCheckFoodStorageTrue() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>());
    foodStorage.addGrocery("apple", "kg", 1.0, 1.0, "01.01.2021");
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertTrue(recipe.checkFoodStorage(foodStorage));
  }

  @Test
  void testCheckFoodStorageFalse() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>());
    foodStorage.addGrocery("apple", "kg", 1.0, 1.0, "01.01.2021");
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 2.0, 1.0);
    assertFalse(recipe.checkFoodStorage(foodStorage));
  }

  @Test
  void testCheckFoodStorageForNonExistingGrocery() {
    FoodStorage foodStorage = new FoodStorage(new ArrayList<>());
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addGrocery("apple", "kg", 1.0, 1.0);
    assertFalse(recipe.checkFoodStorage(foodStorage));
  }

  @Test
  void testAddShortDescription() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addShortDescription("newShortDescription");
    assertEquals(recipe.getShortDescription(), "newShortDescription");
  }

  @Test
  void testAddMethod() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addMethod("newMethod");
    assertEquals(recipe.getMethod(), "newMethod");
  }

  @Test
  void testAddNumberOfPortions() {
    Recipe recipe = new Recipe(new ArrayList<>(), "shortDescription", "method", 1);
    recipe.addNumberOfPortions(2);
    assertEquals(recipe.getNrOfPortions(), 2);
  }
}
