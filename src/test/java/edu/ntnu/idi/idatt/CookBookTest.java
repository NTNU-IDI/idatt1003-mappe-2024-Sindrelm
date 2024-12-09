package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idi.idatt.modules.CookBook;
import edu.ntnu.idi.idatt.modules.FoodStorage;
import edu.ntnu.idi.idatt.modules.Grocery;
import edu.ntnu.idi.idatt.modules.Recipe;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for the CookBook class.
 */
class CookBookTest {

  private static CookBook cookBook;
  private static Recipe pancakeRecipe;
  private static Recipe omeletteRecipe;
  private static FoodStorage foodStorage;

  @BeforeAll
  static void setUp() {
    // Arrange common resources
    ArrayList<Grocery> pancakeGroceries = new ArrayList<>();
    pancakeGroceries.add(new Grocery("Flour", "kg", 1.0, 20.0, "20.12.2024"));
    pancakeGroceries.add(new Grocery("Milk", "l", 1.0, 15.0, "20.12.2024"));
    pancakeGroceries.add(new Grocery("Eggs", "kg", 0.5, 3.0, "20.12.2024"));
    pancakeRecipe = new Recipe(pancakeGroceries, "Pancakes", "Mix all ingredients and fry.", 4);

    ArrayList<Grocery> omeletteGroceries = new ArrayList<>();
    omeletteGroceries.add(new Grocery("Eggs", "kg", 0.3, 3.0, "20.12.2024"));
    omeletteGroceries.add(new Grocery("Milk", "l", 0.5, 15.0, "20.12.2024"));
    omeletteRecipe = new Recipe(omeletteGroceries, "Omelette", "Whisk ingredients and cook.", 2);

    ArrayList<Recipe> recipes = new ArrayList<>();
    recipes.add(pancakeRecipe);
    recipes.add(omeletteRecipe);

    cookBook = new CookBook(recipes);

    foodStorage = new FoodStorage(new ArrayList<>());
    foodStorage.addGrocery("Flour", "kg", 1.0, 20.0, "20.12.2024");
    foodStorage.addGrocery("Milk", "l", 1.5, 15.0, "20.12.2024");
    foodStorage.addGrocery("Eggs", "kg", 0.6, 3.0, "20.12.2024");
  }
  
  @Test
  void testGetRecipeIndex() {
    //Arrange
    setUp();
    // Act
    int index = cookBook.getRecipeIndex("Pancakes");
    // Assert
    assertEquals(0, index);
  }

  @Test
  void testGetRecipeIndexThrowsExceptionForInvalidRecipe() {
    //Arrange
    setUp();
    // Assert
    assertThrows(NoSuchElementException.class, () -> cookBook.getRecipeIndex("Pizza"));
  }

  @Test
  void testGetRecipe() {
    //Arrange
    setUp();
    // Act
    Recipe recipe = cookBook.getRecipe("Pancakes");
    // Assert
    assertEquals("Pancakes", recipe.getShortDescription());
  }

  @Test
  void testGetRecipeThrowsExceptionForInvalidRecipe() {
    //Arrange
    setUp();
    // Assert
    assertThrows(NoSuchElementException.class, () -> cookBook.getRecipe("Pizza"));
  }

  @Test
  void testGetCookBookRegister() {
    //Arrange
    setUp();
    // Act
    String register = cookBook.getCookBookRegister();
    // Assert
    String expected = """
        CookBook Register:
        Pancakes
        Omelette
        """;
    assertEquals(expected.trim(), register.trim());
  }

  @Test
  void testGetRecipeSuggestions() {
    //Arrange
    setUp();
    // Act
    String suggestions = cookBook.getRecipeSuggestions(foodStorage);
    // Assert
    String expected = """
        Recipes you can make with current ingredients in the food storage:
        Pancakes
        Omelette
        """;
    assertEquals(expected.trim(), suggestions.trim());
  }

  @Test
  void testGetRecipeSuggestionsWithMissingGroceries() {
    //Arrange
    setUp();
    // Arrange
    foodStorage.removeGrocery("Flour");
    // Act
    String suggestions = cookBook.getRecipeSuggestions(foodStorage);
    // Assert
    String expected = """
        Recipes you can make with current ingredients in the food storage:
        Omelette
        """;
    assertEquals(expected.trim(), suggestions.trim());
  }

  @Test
  void testAddRecipe() {
    //Arrange
    setUp();
    // Arrange
    ArrayList<Grocery> soupGroceries = new ArrayList<>();
    soupGroceries.add(new Grocery("Vegetables", "kg", 0.5, 25.0, "20.12.2024"));
    Recipe soupRecipe = new Recipe(soupGroceries, "Vegetable Soup", "Boil ingredients.", 3);

    // Act
    cookBook.addRecipe(soupRecipe);

    // Assert
    assertEquals(3, cookBook.getCookBookRegister().split("\n").length - 1); // Excluding header line
    assertEquals("Vegetable Soup", cookBook.getRecipe("Vegetable Soup").getShortDescription());
  }
}
