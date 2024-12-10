package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Grocery;
import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Recipe class.
 */
class RecipeTest {

  private static Recipe pancakeRecipe;
  private static FoodStorage foodStorage;

  @BeforeAll
  static void setUp() {
    ArrayList<Grocery> groceries = new ArrayList<>();
    groceries.add(new Grocery("Flour", "kg", 1.0, 20.0, "20.12.2024"));
    groceries.add(new Grocery("Milk", "l", 1.0, 15.0, "20.12.2024"));
    groceries.add(new Grocery("Eggs", "kg", 0.5, 50, "20.12.2024"));

    pancakeRecipe = new Recipe(groceries, "Pancakes", "Mix all ingredients and fry.", 4);

    foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Flour", "kg", 1.0, 20.0, "20.12.2024");
    foodStorage.addGrocery("Milk", "l", 1.0, 15.0, "20.12.2024");
    foodStorage.addGrocery("Eggs", "kg", 0.5, 50, "20.12.2024");

  }

  @Test
  void testConstructorWithNullGroceries() {
    // Assert
    assertThrows(IllegalArgumentException.class,
        () -> new Recipe(null, "Pancakes", "Mix all ingredients and fry.", 4));
  }

  @Test
  void testConstructorWithEmptyShortDescription() {
    // Assert
    assertThrows(IllegalArgumentException.class,
        () -> new Recipe(pancakeRecipe.getGroceries(), "", "Mix all ingredients and fry.", 4));
  }

  @Test
  void testConstructorWithInvalidNumberOfPortions() {
    // Assert
    assertThrows(IllegalArgumentException.class,
        () -> new Recipe(pancakeRecipe.getGroceries(), "Pancakes", "Mix all ingredients and fry.",
            0));
  }

  @Test
  void testConstructorWithNullMethod() {
    // Assert
    assertThrows(NullPointerException.class,
        () -> new Recipe(pancakeRecipe.getGroceries(), "Pancakes", null, 4));
  }

  @Test
  void testGetGroceries() {
    // Act
    int groceryCount = pancakeRecipe.getGroceries().size();
    // Assert
    assertEquals(3, groceryCount);
  }

  @Test
  void testGetShortDescription() {
    // Act
    String description = pancakeRecipe.getShortDescription();
    // Assert
    assertEquals("Pancakes", description);
  }

  @Test
  void testGetMethod() {
    // Act
    String method = pancakeRecipe.getMethod();
    // Assert
    assertEquals("Mix all ingredients and fry.", method);
  }

  @Test
  void testGetNumberOfPortions() {
    // Act
    int portions = pancakeRecipe.getNumberOfPortions();
    // Assert
    assertEquals(4, portions);
  }

  @Test
  void testGetRecipePrice() {
    // Act
    double recipePrice = pancakeRecipe.getRecipePrice();
    // Assert
    assertEquals(20.0 + 15.0 + (0.5 * 50), recipePrice);
  }

  @Test
  void testAddGrocery() {
    // Act
    pancakeRecipe.addGrocery("Sugar", "kg", 0.5, 10.0);
    int groceryCount = pancakeRecipe.getGroceries().size();
    // Assert
    assertEquals(4, groceryCount);
  }

  @Test
  void testCheckFoodStorage() {
    // Act
    boolean isFoodStorage = pancakeRecipe.checkFoodStorage(foodStorage);
    // Assert
    assertTrue(isFoodStorage);
  }

  @Test
  void testCheckFoodStorageWithMissingGrocery() {
    // Arrange
    foodStorage.removeGrocery("Flour");
    // Act
    boolean isFoodStorage = pancakeRecipe.checkFoodStorage(foodStorage);
    // Assert
    assertFalse(isFoodStorage);
  }

  @Test
  void testGetRecipeInformation() {
    //Arrange
    setUp();
    // Act
    String recipeInformation = pancakeRecipe.getRecipeInformation();
    System.out.println(recipeInformation); // Print the actual output for debugging
    // Assert
    assertEquals("Recipe: Pancakes\n"
        + "Method: Mix all ingredients and fry.\n"
        + "Number of portions: 4\n \n"
        + "Groceries: \n"
        + "Flour, 1.0kg, 20.0kr \n"
        + "Milk, 1.0l, 15.0kr \n"
        + "Eggs, 0.5kg, 25.0kr \n"
        + "\nTotal price: 60.0kr \n", recipeInformation);
  }

}
