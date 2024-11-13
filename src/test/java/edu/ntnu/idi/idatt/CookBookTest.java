package edu.ntnu.idi.idatt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class CookBookTest {

  @Test
  public void testGetRecipeIndexNotFound() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());

    // Act
    try {
      cookBook.getRecipeIndex("Short description");
    } catch (IllegalArgumentException e) {
      // Assert
      assertEquals("Recipe not found", e.getMessage());
    }

  }

  @Test
  public void testGetRecipe() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());
    Recipe recipe1 = new Recipe(new ArrayList<Grocery>(), "Short description 1", "Method 1", 1);
    Recipe recipe2 = new Recipe(new ArrayList<Grocery>(), "Short description 2", "Method 2", 2);
    cookBook.addRecipe(recipe1);
    cookBook.addRecipe(recipe2);

    // Act
    Recipe recipe1FromCookBook = cookBook.getRecipe("Short description 1");
    Recipe recipe2FromCookBook = cookBook.getRecipe("Short description 2");

    // Assert
    assertEquals(recipe1, recipe1FromCookBook);
    assertEquals(recipe2, recipe2FromCookBook);
  }

  @Test
  public void testGetCookBookRegister() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());
    Recipe recipe1 = new Recipe(new ArrayList<Grocery>(), "Short description 1", "Method 1", 1);
    Recipe recipe2 = new Recipe(new ArrayList<Grocery>(), "Short description 2", "Method 2", 2);
    cookBook.addRecipe(recipe1);
    cookBook.addRecipe(recipe2);

    // Act
    String cookBookRegister = cookBook.getCookBookRegister();

    // Assert
    assertEquals("Short description 1\nShort description 2\n", cookBookRegister);
  }

  @Test
  public void testGetRecipeSuggestions() {
    // Arrange
    CookBook cookBook = new CookBook(new ArrayList<Recipe>());
    Recipe recipe1 = new Recipe(new ArrayList<Grocery>(), "Short description 1", "Method 1", 1);
    Recipe recipe2 = new Recipe(new ArrayList<Grocery>(), "Short description 2", "Method 2", 2);
    Recipe recipe3 = new Recipe(new ArrayList<Grocery>(), "Short description 3", "Method 3", 3);
    FoodStorage foodStorage = new FoodStorage(new ArrayList<Grocery>());
    foodStorage.addGrocery("Food 1", "kg", 1, 1, "01.01.2021");
    foodStorage.addGrocery("Food 2", "kg", 1, 1, "01.01.2021");
    recipe1.addGrocery("Food 1", "kg", 1, 1);
    recipe2.addGrocery("Food 2", "kg", 1, 1);
    recipe3.addGrocery("Food 3", "kg", 1, 1);
    cookBook.addRecipe(recipe1);
    cookBook.addRecipe(recipe2);
    cookBook.addRecipe(recipe3);

    // Act
    String recipeSuggestions = cookBook.getRecipeSuggestions(foodStorage);

    // Assert
    assertEquals("Short description 1\nShort description 2\n", recipeSuggestions);
  }

}
